class Anagram
  def initialize(word)
    @word = word
  end

  def match(list)
    list.delete_if { |word| word.downcase == @word.downcase }
    list.keep_if { |word| word.downcase.each_char.to_a.sort == @word.downcase.each_char.to_a.sort }
  end
end
