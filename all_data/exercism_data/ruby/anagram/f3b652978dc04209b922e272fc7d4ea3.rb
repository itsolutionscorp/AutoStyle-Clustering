class Anagram
  def initialize(word)
    @word = word
  end

  def match(list)
    list.select { |i| anagram?(@word, i) }
  end

  private
  def anagram?(a, b)
    return false if a.downcase == b.downcase
    a.downcase.chars.sort == b.downcase.chars.sort
  end
end
