module Hamming
  extend self

  def compute(string1, string2)
    string1.each_char
           .zip(string2.each_char)
           .count { |char1, char2|
             char1 && char2 && char1 != char2
           }
  end
end
