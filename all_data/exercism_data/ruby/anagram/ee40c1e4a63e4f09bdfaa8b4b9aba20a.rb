class Anagram

  def initialize word
    @word = word.downcase
  end

  def match array
    array.select do |word|
      split_word(@word) == split_word(word)
    end
  end

  private

  def split_word word
    word.chars.sort
  end

end
