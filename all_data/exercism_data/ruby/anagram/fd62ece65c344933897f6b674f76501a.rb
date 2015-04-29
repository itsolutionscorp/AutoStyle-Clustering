class Anagram
  def initialize(word)
    @word = word.downcase
    @sorted_word = @word.chars.sort
  end

  def match(list)
    list.delete_if { |word| not_anagram(word.downcase) }
  end

  def not_anagram(word)
    word == @word  or word.chars.sort != @sorted_word
  end
end
