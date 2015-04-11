class Anagram
  attr_reader :word

  def initialize(word)
    @word = word
  end

  def match(list)
    list.select { |w| anagram?(w) }
  end

  def anagram?(other)
    return false if word.casecmp(other) == 0
    format(word) == format(other)
  end

  private

  def format(w)
    w.downcase.chars.sort
  end
end
