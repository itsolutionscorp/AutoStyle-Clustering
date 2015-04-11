class Anagram
  def initialize(word)
    @letters = break_in_letters(word)
  end

  def match(words)
    words.select do |word|
      @letters == break_in_letters(word)
    end
  end

  private

  def break_in_letters(word)
    word.to_s.downcase.chars.sort
  end
end
