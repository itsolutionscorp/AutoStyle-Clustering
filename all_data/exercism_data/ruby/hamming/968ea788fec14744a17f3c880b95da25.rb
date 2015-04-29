module Hamming

  def self.compute str1, str2

    (str1.length > str2.length) ? str1 = str1.slice(0, str2.length) : str2 = str2.slice(0, str1.length)

    str1 = str1.split('')
    str2 = str2.split('')

    hamming_distance = 0
    str1.each_index { |i| (str1[i] != str2[i]) ? hamming_distance += 1 : ''}

    hamming_distance

  end

end
