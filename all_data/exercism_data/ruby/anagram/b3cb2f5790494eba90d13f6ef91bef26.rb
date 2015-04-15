class Anagram
  attr_accessor :input

	def initialize(input)
	 @input = input
  end

	def match(list)
    sorted_input = strip_down_sort(input)

    matches = list.map do |word|
      sorted_word = strip_down_sort(word)     
      if is_anagram?(sorted_input, sorted_word) && not_same_word?(input, word)
        word
      end
    end

    return matches.compact
  end


  private

    def strip_down_sort(raw)
      raw.downcase.chars.sort.join
    end

    def is_anagram?(sorted_input, sorted_word)
      sorted_word == sorted_input
    end

    def not_same_word?(input, word)
      input.downcase != word.downcase
    end

end
