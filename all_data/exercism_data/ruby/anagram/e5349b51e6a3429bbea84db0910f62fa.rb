class Anagram < Struct.new(:word)
  def match(potential_anagrams)
    potential_anagrams.select { |potential_anagram| anagram?(potential_anagram) }
  end

  private

  def anagram?(potential_anagram)
    word.chars.sort == potential_anagram.chars.sort
  end
end
