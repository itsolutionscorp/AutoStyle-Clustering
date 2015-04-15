def distance(string1, string2):
    countDiff = 0
    #loop through both strings to test for differences
    for i in range(0, len(string1)):
        if string1[i] != string2[i]:
            countDiff += 1
    return countDiff


if __name__ == '__main__':
    print(distance('GGACGGATTCTG', 'AGGACGGATTCT'))
