class Hamming
  def compute(str1, str2)
    short_chars, long_chars = [str1, str2].map(&:chars).sort_by(&:size)
    short_chars.zip(long_chars).count { |char1, char2| char1 != char2 }
  end
end
