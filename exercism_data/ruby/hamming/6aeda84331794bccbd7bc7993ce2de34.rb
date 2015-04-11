module Hamming
  def self.compute(string_1, string_2)
    distance = 0
    char_array_1 = string_1.split('')
    char_array_2 = string_2.split('')

    char_array_1.zip(char_array_2).each do |char_1, char_2|
      distance +=1 if char_1 != char_2
    end

    distance
  end
end
