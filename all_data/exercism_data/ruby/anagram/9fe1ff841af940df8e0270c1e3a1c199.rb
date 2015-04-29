class Anagram
  def initialize(anagram)
    @anagram = anagram
    @sorted_letters = sorted_letters(@anagram)
  end

  def match(words)
    words.reject { |word|
      word.casecmp(@anagram) == 0
    }.select { |word|
      sorted_letters(word) == @sorted_letters
    }
  end

  private

  def sorted_letters(word)
    word.downcase.chars.sort
  end
end
