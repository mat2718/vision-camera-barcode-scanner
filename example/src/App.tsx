import * as React from 'react';

import {StyleSheet, Text} from 'react-native';
import {runOnJS} from 'react-native-reanimated';
import {
  Camera,
  useCameraDevices,
  useFrameProcessor,
} from 'react-native-vision-camera';
import {
  Barcode,
  BarcodeFormat,
  scanBarcodes,
} from 'vision-camera-barcode-scanner';

export default function App() {
  const [hasPermission, setHasPermission] = React.useState(false);
  const [barcodes, setBarcodes] = React.useState<Barcode[]>([]);
  const devices = useCameraDevices();
  const device = devices.back;

  const frameProcessor = useFrameProcessor(frame => {
    'worklet';
    const data = scanBarcodes(frame, [BarcodeFormat.ALL_FORMATS], {
      checkInverted: true,
    });
    if (data) {
      runOnJS(setBarcodes)(data);
    }
  }, []);

  React.useEffect(() => {
    (async () => {
      const status = await Camera.requestCameraPermission();
      setHasPermission(status === 'authorized');
    })();
  }, []);

  React.useEffect(() => {
    console.log(barcodes);
  }, [barcodes]);

  return (
    device != null &&
    hasPermission && (
      <>
        <Camera
          style={StyleSheet.absoluteFill}
          device={device}
          isActive={true}
          frameProcessor={frameProcessor}
          frameProcessorFps={5}
          zoom={device?.neutralZoom ?? 1}
        />
        {barcodes.map((barcode, idx) => (
          <Text key={idx} style={styles.barcodeTextURL}>
            {barcode.displayValue}
          </Text>
        ))}
      </>
    )
  );
}

const styles = StyleSheet.create({
  barcodeTextURL: {
    fontSize: 20,
    color: 'white',
    fontWeight: 'bold',
  },
});
