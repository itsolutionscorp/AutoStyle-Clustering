class Anagram
  def initialize(string)
    @string = string.downcase
  end

  def match(words_to_match)
    anagrams = []
    words_to_match.each do |word|
      if is_anagram?(word.downcase)
        anagrams << word
      end
    end
    anagrams
  end

  private
  def is_anagram?(word)
    word.chars.sort == @string.chars.sort && word != @string
  end
end
