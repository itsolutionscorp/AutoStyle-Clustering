class Anagram
  def initialize(word)
    @word = word
  end

  def match(list)
    list.delete_if { |word| not_anagram(word) }
  end

  def not_anagram(word)
    word = word.downcase
    initial = @word.downcase
    word == initial or word.chars.sort != initial.chars.sort
  end
end
