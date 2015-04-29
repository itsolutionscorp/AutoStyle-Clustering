def slices(inputStr, strLength):
    try:
        if (strLength == 0 or strLength > len(inputStr)):
            raise ValueError('Desired length of series is greater than length of string')
        retArray = []
        for i in range(len(inputStr) - strLength + 1):
            loopArray = []
            for i in inputStr[i : i + strLength]:
                loopArray.append(int(i))
            retArray.append(loopArray)
        return retArray
    except ValueError:
        print('Desired length of series is greater than length of string')


if __name__ == '__main__':
    print(slices("01234", 0))
