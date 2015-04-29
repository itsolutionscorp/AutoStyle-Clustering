class Anagram
  attr_reader :word

  def initialize(word)
    @word = word
  end

  def match(words_array = [])
    words_array.select do |this_word|
      this_word if is_alphagram?(this_word) && not_same_words?(this_word)
    end
  end

  private

    def is_alphagram?(word_arg)
      sort_alphabetically(word_arg) == sort_alphabetically(word)
    end

    def sort_alphabetically(word_arg)
      word_arg.downcase.chars.sort.join
    end

    def not_same_words?(word_arg)
      word_arg.downcase != word.downcase
    end

end
