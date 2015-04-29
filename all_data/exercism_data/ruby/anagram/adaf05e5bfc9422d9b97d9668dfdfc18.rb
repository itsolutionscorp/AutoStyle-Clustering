class Anagram
  def initialize(word)
    @word = word.downcase
  end

  def match(arr)
    base_word = @word.each_char.sort
    arr.select do |word|
      word.downcase.each_char.sort == base_word &&
        word.downcase != @word
    end
  end
end
