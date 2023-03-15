import type {Frame} from 'react-native-vision-camera';
import type {Barcode, BarcodeFormat, CodeScannerOptions} from '../types/types';

/**
 * Scans barcodes in the passed frame with MLKit
 *
 * @param frame Camera frame
 * @param types Array of barcode types to detect (for optimal performance, use less types)
 * @returns Detected barcodes from MLKit
 */
export default function scanBarcodes(
  frame: Frame,
  types: BarcodeFormat[],
  options?: CodeScannerOptions,
): Barcode[] {
  'worklet';
  // @ts-ignore
  // eslint-disable-next-line no-undef
  return __scanBarcodes(frame, types, options);
}
