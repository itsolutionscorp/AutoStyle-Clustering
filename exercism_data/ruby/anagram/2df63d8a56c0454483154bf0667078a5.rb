class Anagram
  def initialize(word)
    @word = word.downcase
    @normalized_word = normalize(word)
  end

  def match(possibilities)
    possibilities.select { |possible| is_anagram?(possible) }
  end

  private
  attr_reader :word, :normalized_word

  def normalize(word)
    word.downcase.split('').sort.join
  end

  def is_anagram?(possible)
    possible.downcase != word &&
      normalize(possible) == normalized_word
  end
end
