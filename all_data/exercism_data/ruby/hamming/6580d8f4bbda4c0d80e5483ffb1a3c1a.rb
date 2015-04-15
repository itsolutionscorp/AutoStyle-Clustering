module Hamming
  def self.compute(string_1, string_2)
    string_1.chars.zip(string_2.chars).count do |char_1, char_2|
      char_1 != char_2
    end
  end
end
