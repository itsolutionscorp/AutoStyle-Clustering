class Anagram

  attr_reader :word
  def initialize(word)
    @word = word
  end

  def match(list)
    ordered_chars = sort_chars(@word)
    list.select do |word|
      ordered_chars == sort_chars(word)
    end
  end

  private

    def sort_chars(word)
      word.downcase.chars.sort
    end

end
