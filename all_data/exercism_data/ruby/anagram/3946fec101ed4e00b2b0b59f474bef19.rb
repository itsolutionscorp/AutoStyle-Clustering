class Anagram

  attr_reader :word

  def initialize(word)
    @word = word.downcase
  end

  def match(array)
    array.map { |w| w if is_anagram?(w) && !is_same_word?(w) }.compact
  end

  def is_anagram?(w)
    word.chars.sort.join == w.downcase.chars.sort.join
  end

  def is_same_word?(w)
    word == w.downcase
  end

end
