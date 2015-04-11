class Anagram

	def initialize(word)
		@word=word
	end

	def match(word_list)
    word_list.each_with_object([]) do |word, array|
      next if word.downcase == @word
      array << word if permutations.include?(word.downcase)
    end
	end

  def permutations
    @word.downcase.split("").permutation.map(&:join)
  end

end
