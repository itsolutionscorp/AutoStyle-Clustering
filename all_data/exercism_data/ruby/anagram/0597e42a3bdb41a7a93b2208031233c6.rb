class Anagram
  def initialize(word)
    @word = word
  end

  def match(list)
    list.delete_if { |word| same(word) or not_anagram(word) }
  end

  def same(word)
    word.downcase == @word.downcase
  end

  def not_anagram(word)
    word.downcase.chars.sort != @word.downcase.chars.sort
  end
end
