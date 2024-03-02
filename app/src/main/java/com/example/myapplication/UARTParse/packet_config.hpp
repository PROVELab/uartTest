#ifndef PCAN_UART
#define PCAN_UART

//------------------------------//
#include <cstdint>
#include <stdio.h>
//------------------------------//
#define SENTINEL -2147483648 
#define HEADER_SIZE_BITS 16
#define HEADER_SIZE_BYTES 2
#define MAX_DATA_SIZE_BITS 50
#define MAX_DATA_SIZE_BYTES 7
#define CRC_SIZE_BITS 16
#define CRC_SIZE_BYTES 2

//------------------------------//
#define SENSOR_CT 10
#define DATA_STRUCT_SIZE 50

//------------------------------//

// Polynomials for CRC stuff
static uint16_t polys[] = {0xed2f, 0xe92f, 0x8fdb, 0x968b, 0x9eb2};

#pragma pack(1)
struct __attribute__((__packed__)) Sensor_Data {
    //TirePressureSensor1
    uint8_t TP1_size = 4;
    int32_t TP1_data = SENTINEL;
    //TirePressureSensor2
    uint8_t TP2_size = 4;
    int32_t TP2_data = SENTINEL;

    uint8_t TP3_size = 4;
    int32_t TP3_data = SENTINEL;

    uint8_t TP4_size = 4;
    int32_t TP4_data = SENTINEL;

    uint8_t S_size = 6;
    int32_t S_data = SENTINEL;

    uint8_t S2_size = 6;
    int32_t S2_data = SENTINEL;

    uint8_t S3_size = 6;
    int32_t S3_data = SENTINEL;
    //Shynnisthebestsensor4
    uint8_t S4_size = 6;
    int32_t S4_data = SENTINEL;

    uint8_t S5_size = 6;
    int32_t S5_data = SENTINEL;
    //Nobodygivesadamn?
    uint8_t ZeroDamns_size = 4;
    int32_t ZeroDamns_data = SENTINEL;
};
#endif