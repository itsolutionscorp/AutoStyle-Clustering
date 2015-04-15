class Anagram
  attr_reader :word

  def initialize(word)
    @word = word
  end

  def match(anagrams)
    anagrams.select { |anagram| permutations.include?(anagram) }
  end

  def permutations
    permutations = []
    word.split('').permutation.to_a.each do |p|
      permutations << p.join
    end
    permutations
  end

end
