class Hamming
  def self.compute(str1, str2)
    str1.chars.each.with_index.count do |char, index|
      str2[index] && str2[index] != char
    end
  end
end
