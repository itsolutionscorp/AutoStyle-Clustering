class Anagram
  def initialize(word)
    @word = word
  end

  def match(possible_anagrams)
    possible_anagrams.find_all do |anagram|
      permutations_except_self.include?(anagram.downcase)
    end
  end

private
  def permutations_except_self
    @_permutations_except_self ||= permutations - [@word]
  end

  def permutations
    @_permutations ||= each_character.permutation.map(&:join)
  end

  def each_character
    @_each_character ||= @word.downcase.chars
  end
end
