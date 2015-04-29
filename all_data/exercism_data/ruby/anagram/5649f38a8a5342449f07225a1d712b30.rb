class Anagram

  def initialize word
    @word = word
  end

  def match array
    array.select do |possible_word|
      possible_word.downcase != lowercase_word and permutations_of_word.include? possible_word.downcase
    end
  end

private

  def permutations_of_word
    @permutations ||= lowercase_word.chars.permutation.map(&:join)
  end

  def lowercase_word
    @word.downcase
  end

end
