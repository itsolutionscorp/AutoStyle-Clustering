class Anagram

  attr_reader :letters

  def initialize(word)
    @letters = letters_from(word)
  end

  def match(sample_words)
    sample_words.select { |word| word if anagram?(word) }
  end

  private

  def anagram?(word)
    letters_from(word) == letters 
  end

  def letters_from(word)
    word.split("").sort
  end
end
