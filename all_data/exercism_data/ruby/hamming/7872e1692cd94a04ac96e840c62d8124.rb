class Hamming
  def self.compute(one, two)
    one.chars.zip(two.chars).count do |one_char, two_char|
       one_char && two_char && one_char != two_char
    end
  end
end
