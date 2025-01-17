/**
 * @see https://developers.google.com/android/reference/com/google/mlkit/vision/barcode/common/Barcode.BarcodeFormat
 */
export enum BarcodeFormat {
  UNKNOWN = -1,
  ALL_FORMATS = 0,
  CODE_128 = 1,
  CODE_39 = 2,
  CODE_93 = 4,
  CODABAR = 8,
  DATA_MATRIX = 16,
  EAN_13 = 32,
  EAN_8 = 64,
  ITF = 128,
  QR_CODE = 256,
  UPC_A = 512,
  UPC_E = 1024,
  PDF417 = 2048,
  AZTEC = 4096,
}

/**
 * @see https://developers.google.com/android/reference/com/google/mlkit/vision/barcode/common/Barcode.BarcodeValueType
 */
export enum BarcodeValueType {
  UNKNOWN = 0,
  CONTACT_INFO = 1,
  EMAIL = 2,
  ISBN = 3,
  PHONE = 4,
  PRODUCT = 5,
  SMS = 6,
  TEXT = 7,
  URL = 8,
  WIFI = 9,
  GEO = 10,
  CALENDAR_EVENT = 11,
  DRIVER_LICENSE = 12,
}

/**
 * @see https://developers.google.com/android/reference/com/google/mlkit/vision/barcode/common/Barcode.Address.AddressType
 */
export enum AddressType {
  UNKNOWN = 0,
  WORK = 1,
  HOME = 2,
}

/**
 * @see https://developers.google.com/android/reference/com/google/mlkit/vision/barcode/common/Barcode.Address
 */
export interface Address {
  addressLines?: string[];
  type?: AddressType;
}

/**
 * @see https://developers.google.com/android/reference/com/google/mlkit/vision/barcode/common/Barcode.PersonName
 */
export interface PersonName {
  first?: string;
  formattedName?: string;
  last?: string;
  middle?: string;
  prefix?: string;
  pronunciation?: string;
  suffix?: string;
}

/**
 * @see https://developers.google.com/android/reference/com/google/mlkit/vision/barcode/common/Barcode.ContactInfo
 */
export interface ContactInfo {
  addresses?: Address[];
  emails?: Email[];
  name?: PersonName;
  organization?: string;
  phones?: Phone[];
  title?: string;
  urls?: string[];
}

/**
 * @see https://developers.google.com/android/reference/com/google/mlkit/vision/barcode/common/Barcode.Email.FormatType
 */
export enum EmailType {
  UNKNOWN = 0,
  WORK = 1,
  HOME = 2,
}

/**
 * @see https://developers.google.com/android/reference/com/google/mlkit/vision/barcode/common/Barcode.Email
 */
export interface Email {
  address?: string;
  body?: string;
  subject?: string;
  type?: EmailType;
}

/**
 * @see https://developers.google.com/android/reference/com/google/mlkit/vision/barcode/common/Barcode.Phone.FormatType
 */
export enum PhoneType {
  UNKNOWN = 0,
  WORK = 1,
  HOME = 2,
  FAX = 3,
  MOBILE = 4,
}

/**
 * @see https://developers.google.com/android/reference/com/google/mlkit/vision/barcode/common/Barcode.Phone
 */
export interface Phone {
  number?: string;
  type?: PhoneType;
}

/**
 * @see https://developers.google.com/android/reference/com/google/mlkit/vision/barcode/common/Barcode.Sms
 */
export interface Sms {
  message?: string;
  phoneNumber?: string;
}

/**
 * @see https://developers.google.com/android/reference/com/google/mlkit/vision/barcode/common/Barcode.UrlBookmark
 */
export interface UrlBookmark {
  title?: string;
  url?: string;
}

/**
 * @see https://developers.google.com/android/reference/com/google/mlkit/vision/barcode/common/Barcode.WiFi.EncryptionType
 */
export enum EncryptionType {
  OPEN = 1,
  WPA = 2,
  WEP = 3,
}

/**
 * @see https://developers.google.com/android/reference/com/google/mlkit/vision/barcode/common/Barcode.WiFi
 */
export interface Wifi {
  encryptionType?: EncryptionType;
  password?: string;
  ssid?: string;
}

/**
 * @see https://developers.google.com/android/reference/com/google/mlkit/vision/barcode/common/Barcode.GeoPoint
 */
export interface GeoPoint {
  lat?: number;
  lng?: number;
}

/**
 * @see https://developers.google.com/android/reference/com/google/mlkit/vision/barcode/common/Barcode.CalendarDateTime
 */
export interface Date {
  day: number;
  hours: number;
  minutes: number;
  month: number;
  rawValue: string;
  seconds: number;
  year: number;
  isUtc: boolean;
}

/**
 * @see https://developers.google.com/android/reference/com/google/mlkit/vision/barcode/common/Barcode.CalendarEvent
 */
export interface CalendarEvent {
  description?: string;
  end?: Date;
  location?: string;
  organizer?: string;
  start?: Date;
  status?: string;
  summary?: string;
}

/**
 * @see https://developers.google.com/android/reference/com/google/mlkit/vision/barcode/common/Barcode.DriverLicense
 */
export interface DriverLicense {
  addressCity?: string;
  addressState?: string;
  addressStreet?: string;
  addressZip?: string;
  birthDate?: string;
  documentType?: string;
  expiryDate?: string;
  firstName?: string;
  gender?: string;
  issueDate?: string;
  issuingCountry?: string;
  lastName?: string;
  licenseNumber?: string;
  middleName?: string;
}

/**
 * Rect is an object with four properties: top, left, height, and width, all of which are numbers.
 * @property {number} top - The top position of the element.
 * @property {number} left - The x-coordinate of the top-left corner of the rectangle.
 * @property {number} height - The height of the element.
 * @property {number} width - The width of the element.
 */
export type Rect = {
  /** the y coordinate in the exact center of the barcode bounds */
  boundingCenterY?: number;
  /** the x coordinate in the exact center of the barcode bounds */
  boundingCenterX?: number;
  /** the top of the barcode bounds */
  top: number;
  /** the left of the barcode bounds */
  left: number;
  /** the bottom of the barcode bounds */
  bottom?: number;
  /** the right of the barcode bounds */
  right?: number;
  /** the height of the barcode bounds */
  height: number;
  /** the width of the barcode bounds */
  width: number;
  x?: number;
  y?: number;
};

/**
 * Dimensions is an object with a width property that is a number and a height property that is a
 * number.
 * @property {number} width - The width of the image in pixels.
 * @property {number} height - The height of the image in pixels.
 */
export type Dimensions = {width: number; height: number};

/**
 * @see https://developer.android.com/reference/android/graphics/Point.html
 */
export interface Point {
  x: number;
  y: number;
}

/**
 * @see https://developers.google.com/android/reference/com/google/mlkit/vision/barcode/common/Barcode
 */
export type Barcode = {
  boundingBox?: Rect;
  cornerPoints?: Point[];
  displayValue?: string;
  rawValue?: string;
  format: BarcodeFormat;
  content:
    | {
        type:
          | BarcodeValueType.UNKNOWN
          | BarcodeValueType.ISBN
          | BarcodeValueType.TEXT;
        data: string;
      }
    | {
        type: BarcodeValueType.CONTACT_INFO;
        data: ContactInfo;
      }
    | {
        type: BarcodeValueType.EMAIL;
        data: Email;
      }
    | {
        type: BarcodeValueType.PHONE;
        data: Phone;
      }
    | {
        type: BarcodeValueType.SMS;
        data: Sms;
      }
    | {
        type: BarcodeValueType.URL;
        data: UrlBookmark;
      }
    | {
        type: BarcodeValueType.WIFI;
        data: Wifi;
      }
    | {
        type: BarcodeValueType.GEO;
        data: GeoPoint;
      }
    | {
        type: BarcodeValueType.CALENDAR_EVENT;
        data: CalendarEvent;
      }
    | {
        type: BarcodeValueType.DRIVER_LICENSE;
        data: DriverLicense;
      };
};

export interface CodeScannerOptions {
  /**
   * checkInverted: `Allows you to also scan white barcode with black backgrounds`
   */
  checkInverted?: boolean;
}
