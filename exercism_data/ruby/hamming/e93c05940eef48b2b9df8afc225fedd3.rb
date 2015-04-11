class Hamming
  def self.compute(str1, str2)
    short_str, long_str = [str1, str2].sort_by(&:size)
    short_chars, long_chars = short_str.chars, long_str.chars
    short_chars.zip(long_chars).count { |char1, char2| char1 != char2 }
  end
end
