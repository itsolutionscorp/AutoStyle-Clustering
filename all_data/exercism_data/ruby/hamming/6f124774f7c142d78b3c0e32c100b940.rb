class Hamming
  def self.compute(str1, str2)
    str1.chars.each_with_index.count {|char, index| char != str2[index]}
  end
end
