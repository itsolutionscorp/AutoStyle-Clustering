class Anagram
  attr_accessor :input

  def initialize(input)
    @input = input
  end

	def match(list)
    sorted_input = strip_sort(input)

    matches = list.map do |word|
      sorted_word = strip_sort(word)     
      if anagram(sorted_input, sorted_word) && same_word(input, word)
        word
      end
    end

    return matches.compact
  end


  private

    def strip_sort(raw)
      raw.downcase.chars.sort.join
    end

    def anagram(sorted_input, sorted_word)
      sorted_word == sorted_input
    end

    def same_word(input, word)
      input.downcase != word.downcase
    end

end
