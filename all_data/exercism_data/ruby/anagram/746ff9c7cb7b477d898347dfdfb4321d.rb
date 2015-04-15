class Anagram
  def initialize(word)
    @base_word = word.downcase
  end

  def match(words)
    words.find_all do |word| 
      anagram_word?(word)
    end
  end

  def anagram_word?(word)
    word.downcase.chars.sort == @base_word.chars.sort and
    word.downcase != @base_word
  end
end
