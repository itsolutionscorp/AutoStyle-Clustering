class Anagram

  attr_reader :word
  def initialize(word)
    @word = word
  end

  def match(word_list)
    sorted_word = alphabatize(@word)
    word_list.select do |word|
      sorted_word == alphabatize(word)
    end
  end

  private

    def alphabatize(word)
      word.downcase.chars.sort
    end

end
