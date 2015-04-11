class Anagram
  def initialize(word)
    @base_word = word.downcase
  end

  def match(words)
    words.reject do |word| 
      non_anagram_word?(word)
    end
  end

  def non_anagram_word?(word)
    word.downcase.chars.sort != @base_word.chars.sort or
    word.downcase == @base_word
  end
end
