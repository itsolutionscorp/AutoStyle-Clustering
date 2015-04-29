Anagram = Struct.new(:word) do
  def match(possible_matches)
    possible_matches.select do |match|
      word_permutations.include?(match)
    end
  end

  def word_permutations
    @permutations ||= word.downcase.chars.permutation.map(&:join)
  end
end
