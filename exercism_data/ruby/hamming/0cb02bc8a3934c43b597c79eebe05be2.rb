class Hamming
  def self.compute(string1, string2)
    strings = [string1, string2]
    sorted_strings = sort_by_length(strings)
    zipped_chars = zip_strings(sorted_strings)
    zipped_chars.count { |char_pair| char_pair[0] != char_pair[1] }9ik
  end
  def self.zip_strings(strings)
    strings[0].chars.zip(strings[1].chars)
  end
  def self.sort_by_length(strings)
    strings.sort_by { |word| word.length }
  end
end
