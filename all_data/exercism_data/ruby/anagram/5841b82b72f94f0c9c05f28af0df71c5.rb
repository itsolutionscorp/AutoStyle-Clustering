class Anagram

  def initialize(word)
    @word = word
  end

  def match(list)
    remove_identicals_in(list).select{ |item| anagram?(item) }
  end

  private
    attr_accessor :word

    def anagram?(other_word)
      sort_chars(word) == sort_chars(other_word)
    end

    def remove_identicals_in(list)
      list.reject{ |item| item.downcase == word.downcase }
    end

    def sort_chars(word)
      word.downcase.chars.sort
    end
end
