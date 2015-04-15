class Anagram

  def initialize(word)
    @word = word
  end

  def match(words)
    words.each_with_object([]) do |word, anagrams|
      anagrams << word if anagram? word
    end
  end

  private

  def anagram?(word)
    !same_word?(@word, word) && same_letters?(@word, word)
  end

  def same_word?(original, compare)
    original.downcase == compare.downcase
  end

  def same_letters?(original, compare)
    sort_word(original) == sort_word(compare)
  end

  def sort_word(word)
    word.downcase.chars.sort
  end
end
