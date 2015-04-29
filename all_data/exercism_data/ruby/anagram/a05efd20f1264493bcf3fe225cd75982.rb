class Anagram

  def initialize(source)
    @source_letters = sorted_letters(source)
  end

  def match(potential_anagrams)
    potential_anagrams.select { |word| anagram?(word) }
  end

  private

  def anagram?(word)
    sorted_letters(word) == @source_letters
  end

  def sorted_letters(word)
    word.chars.sort
  end
end
