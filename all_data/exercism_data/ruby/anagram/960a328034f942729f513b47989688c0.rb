class Anagram

  attr_reader :test_letters

  def initialize(word)
    @test_letters = letters_from(word)
  end

  def match(sample_words)
    matches = []
    sample_words.each do |word|
      matches << word if anagram?(word)
    end
    matches
  end

  private

  def anagram?(word)
    letters = word.split("").sort
    letters == test_letters 
  end

  def letters_from(word)
    word.split("").sort
  end
end
