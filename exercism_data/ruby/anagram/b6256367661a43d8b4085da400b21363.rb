class Anagram
  def initialize(sequence)
    @sequence = sequence
  end

  def match(anagrams)
    anagrams.select { |anagram| anagram?(anagram) }
  end

  def anagram?(anagram)
    anagram.downcase.sum == @sequence.downcase.sum
  end
end
