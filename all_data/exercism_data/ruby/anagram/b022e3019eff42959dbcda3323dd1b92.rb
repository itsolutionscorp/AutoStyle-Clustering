class Anagram
  attr_accessor :word, :matches_array

  def initialize(word)
    @word = word
    @matches_array = []
  end

  def match(words_array = [])
    words_array.each do |testing_word|
      if this_word_passes_the_test? testing_word
        matches_array << testing_word
      end
    end
    matches_array
  end

  private

    def sort_chars_alphabetically(word)
      word.downcase.chars.sort.join
    end

    def is_this_an_anagram?(this_word)
      sort_chars_alphabetically(this_word) == sort_chars_alphabetically(word)
    end

    def not_the_same_word?(this_word)
      this_word.downcase != word.downcase
    end

    def this_word_passes_the_test?(this_word)
      is_this_an_anagram?(this_word) &&
      not_the_same_word?(this_word) &&
      this_word != word
    end

end
