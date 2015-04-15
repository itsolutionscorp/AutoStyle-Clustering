class Anagram
  attr_accessor :word, :matches_array

  def initialize(word)
    @word = word
    @matches_array = []
  end

  def match(words_array = [])
    words_array.each_with_object([]) do |w, a|
      if is_alphagram?(w) && not_same_words?(w)
        a << w
      end
    end
  end

  private

    def is_alphagram?(this_word)
      sort_alphabetically(this_word) == sort_alphabetically(word)
    end

    def sort_alphabetically(this_word)
      this_word.downcase.chars.sort.join
    end

    def not_same_words?(this_word)
      this_word.downcase != word.downcase
    end

end
