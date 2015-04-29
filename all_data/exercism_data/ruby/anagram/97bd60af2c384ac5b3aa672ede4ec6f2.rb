class Anagram
  def initialize(string)
    @string = string
  end

  def match(words_to_match)
    anagrams = []
    words_to_match.each do |word|
      anagrams << word if is_anagram?(word)
    end
    anagrams
  end

  private
  def is_anagram?(word)
    word.downcase.scan(/\w/).sort == @string.downcase.scan(/\w/).sort && word.downcase != @string.downcase
  end
end
