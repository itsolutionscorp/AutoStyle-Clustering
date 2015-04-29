class Anagram
  attr_reader :word
  def initialize(word)
    @word = word.downcase
  end

  def match(array)
    array.each_with_object([]) do |anagram, matches|
      next unless anagram.size.eql?(word.size) && anagram.downcase != word
      matches << anagram if anagram.downcase.chars.sort == word.chars.sort
      end
  end
end
