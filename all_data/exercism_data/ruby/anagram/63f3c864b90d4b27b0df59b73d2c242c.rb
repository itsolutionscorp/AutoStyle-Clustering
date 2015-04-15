class Anagram
  def initialize(word)
    @word = word
  end

  def is_anagram?(w1, w2)
    w1, w2 = normalize(w1), normalize(w2)
    has_same_letters?(w1, w2) && is_different_word?(w1, w2)
  end

  def normalize(word)
    word.downcase
  end

  def has_same_letters?(w1, w2)
    characters(w1).sort == characters(w2).sort
  end

  def is_different_word?(w1, w2)
    w1 != w2
  end

  def characters(word)
    word.split(//)
  end

  def match(words)
    [*words].select { |word| is_anagram?(@word, word) }
  end
end
