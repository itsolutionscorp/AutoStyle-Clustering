class Anagram < Struct.new(:word)
  def match(potential_anagrams)
    potential_anagrams.each_with_object([]) do |potential_anagram, anagrams|
      anagrams << potential_anagram if anagram?(potential_anagram)
    end
  end

  private

  def anagram?(potential_anagram)
    word.split('').sort == potential_anagram.split('').sort
  end
end
