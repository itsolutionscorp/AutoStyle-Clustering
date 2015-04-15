class Anagram
  def initialize(word)
    @word = word.downcase
  end

  def match(list)
    list.select do |word|
      word = word.downcase
      letters_match?(word) && word != @word
    end
  end

  private

  def letters_match?(word)
    word.chars.sort == @word.chars.sort
  end
end
