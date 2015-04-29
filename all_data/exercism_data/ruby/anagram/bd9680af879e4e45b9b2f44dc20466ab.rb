class Anagram
  def initialize(word)
    @word = word
  end

  def match(possible_words)
    possible_words.select do |word|
      word.downcase != @word.downcase &&
        break_down(word) == comparable_word
    end.sort
  end

  private

  def break_down(word)
    word.downcase.chars.sort
  end

  def comparable_word
    @comparable_word ||= break_down(@word)
  end
end
