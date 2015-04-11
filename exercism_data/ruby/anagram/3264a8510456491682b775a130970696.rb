class Anagram

  def initialize(word)
    @word = word.downcase
  end

  def match(anagrams)
    anagrams.each_with_object([]) do |anagram, matches|
      matches << anagram if permutations.include?(anagram.downcase)
    end.flatten.uniq
  end

  private

  attr_reader :word

  def permutations
    @permutations ||= word.split("").permutation.to_a.map(&:join).uniq - [word]
  end

end
