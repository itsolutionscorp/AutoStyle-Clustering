def numeral(arabic):
    rom_nums = {1:    'I',
                4:    'IV',
                5:    'V',
                9:    'IX',
                10:   'X',
                40:   'XL',
                50:   'L',
                90:   'XC',
                100:  'C',
                400:  'CD',
                500:  'D',
                900:  'CM',
                1000: 'M'}


    rom = ''
    for key in sorted(rom_nums.keys(), reverse=True):
        cnt = arabic // key
        rom += rom_nums[key] * cnt
        arabic -= key * cnt

    return rom

if __name__ == "__main__":
    print(1, numeral(1))
    print(9, numeral(9))
    print(15, numeral(15))
    print(90, numeral(90))
    print(101, numeral(101))
    print(2051, numeral(2051))
