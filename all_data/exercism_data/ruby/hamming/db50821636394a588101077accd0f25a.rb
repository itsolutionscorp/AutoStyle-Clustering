class Hamming

  def self.compute(string_1, string_2)
    differences = 0
    i = 0
    string_1.each_char do |letter|
      letter_2 = string_2[i]
      break if letter_2 == nil
      differences += 1 if letter != letter_2
      i += 1
    end
    differences
  end

end
