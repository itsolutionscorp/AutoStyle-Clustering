class Anagram

  def initialize word
    @word = word.downcase
  end

  def match array
    array.select do |word|
      normalize_word(@word) == normalize_word(word)
    end
  end

  private

  def normalize_word word
    word.chars.sort
  end

end
