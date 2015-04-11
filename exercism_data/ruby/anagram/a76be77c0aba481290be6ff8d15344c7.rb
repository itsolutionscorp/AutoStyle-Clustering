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
    (
      !same_word?(@word, word)  &&
      same_length?(@word, word) &&
      same_letters?(@word, word)
    )
  end

  def same_word?(original, compare)
    original.downcase == compare.downcase
  end

  def same_length?(original, compare)
    original.length == compare.length
  end

  def same_letters?(original, compare)
    same_word? sort_word(original), sort_word(compare)
  end

  def sort_word(word)
    word.downcase.scan(/./).sort.join
  end
end
