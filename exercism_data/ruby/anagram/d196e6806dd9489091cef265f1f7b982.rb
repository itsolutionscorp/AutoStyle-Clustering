class Anagram < Struct.new :source

  def match(potential_anagrams)
    potential_anagrams.select { |word| anagram?(word) }
  end

  private

  def anagram?(word)
    sorted_letters(word) == sorted_letters(source)
  end

  def sorted_letters(word)
    word.chars.sort
  end
end
