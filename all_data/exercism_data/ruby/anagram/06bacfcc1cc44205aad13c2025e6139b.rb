class Anagram

	def initialize(anagram)
		@anagram = anagram
	end

	def match(word_list)
    word_list.each_with_object([]) do |word, array|
      next if same_word?(word, @anagram)
      array << word if anagram?(word, @anagram)
    end
	end

  private

    def sort_word(word)
      word.downcase.chars.sort
    end

    def same_word?(word1, word2)
      word1.downcase == word2.downcase
    end

    def anagram?(word1, word2)
      sort_word(word1) == sort_word(word2)
    end

end
