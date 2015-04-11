class Anagram

  def initialize(base_word)
    @base_word = base_word
  end

  def match(word_list)
    word_list.select { |word| anagram?(word) }
  end

  private

    def same_word?(word)
      word.downcase == @base_word.downcase
    end

    def sort_word(word)
      word.downcase.chars.sort
    end

    def same_letters?(word)
      sort_word(word) == sort_word(@base_word)
    end

    def anagram?(word)
      !same_word?(word) && same_letters?(word)
    end

end
