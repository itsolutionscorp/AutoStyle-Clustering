class Anagram
  attr_accessor :word

  def initialize(word)
    @word=word.downcase
  end

  def match(wordarray)
    wordarray.select {|match| match.downcase.chars.sort == word.chars.sort && match.downcase != word}
  end

end
