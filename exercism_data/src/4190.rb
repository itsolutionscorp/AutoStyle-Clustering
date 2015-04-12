class Hamming

  def compute str1, str2

    # ensure that the strings are of equal length
    (str1.length > str2.length) ? str1 = str1.slice(0, str2.length) : str2 = str2.slice(0, str1.length)

    # split strings into an array
    str1 = str1.split('')
    str2 = str2.split('')

    hamming_distance = 0

    # compare both arrays for calculating the hamming_distance
    str1.each_index { |i| (str1[i] != str2[i]) ? hamming_distance += 1 : ''}

    hamming_distance

  end

end
