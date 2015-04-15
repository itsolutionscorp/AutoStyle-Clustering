class Anagram

  attr_reader :word

  def initialize(word)
    @word = word.downcase
  end

  def match(anagrams)
    anagrams.select do |anagram|
      anagram? anagram.downcase
    end
  end

  private

  def anagram?(potential_anagram)
    return false if potential_anagram.eql? word
    word.chars.sort.eql? potential_anagram.chars.sort
  end

end
