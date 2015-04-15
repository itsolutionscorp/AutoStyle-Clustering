def compute str1, str2

    # ensure that the strings are of equal length
    (str1.length > str2.length) ? str1 = str1.slice(0, str2.length) : str2 = str2.slice(0, str1.length)

    hamming_distance = 0

    # compare both arrays for calculating the hamming_distance
    (0..str1.length).each { |index| hamming_distance += 1 if (str1[index] != str2[index]) }

    hamming_distance

  end