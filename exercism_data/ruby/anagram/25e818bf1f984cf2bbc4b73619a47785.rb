class Anagram

  def initialize word
    @word = word.downcase
  end

  def match array
    array.select do |possible_word|
      where_permutations_include_but_dont_match possible_word.downcase
    end
  end

private

  def where_permutations_include_but_dont_match word
    word != @word and permutations_of_word.include? word
  end

  def permutations_of_word
    @permutations ||= @word.chars.permutation.map(&:join)
  end

end
