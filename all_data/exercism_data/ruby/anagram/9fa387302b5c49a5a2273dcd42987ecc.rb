class Anagram
  def initialize(word)
    @word = word
  end

  def match(list)
    list.select { |i| anagram?(@word.downcase, i.downcase) }
  end

  private
  def anagram?(a, b)
    return false if a == b
    a.chars.sort == b.chars.sort
  end
end
