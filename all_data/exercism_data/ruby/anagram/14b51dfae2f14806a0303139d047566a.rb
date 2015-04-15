class Anagram
  def initialize(word)
    @word = word.downcase
    @letters = @word.chars.sort
  end

  def match(words)
    words.select do |word|
      downcased_word = word.downcase
      different?(downcased_word) && same_letters?(downcased_word)
    end
  end

  private

  def different?(word)
    word != @word
  end

  def same_letters?(word)
    word.chars.sort == @letters
  end
end
