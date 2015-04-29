class Anagram

  attr_reader :word
  def initialize(word)
    @word = word
  end

  def match(list)
    alphabatized_word = alphabatized(@word)
    list.select do |word|
      alphabatized_word == alphabatized(word)
    end
  end

  private

    def alphabatized(word)
      word.downcase.split('').sort.join
    end

end
