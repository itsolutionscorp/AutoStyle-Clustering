class Anagram
  def initialize(word)
    @word = word.downcase
    @normalized_word = normalize(word)
  end

  def match(possibilities)
    possibilities.reduce([]) do |acc, possible|
      if is_anagram?(possible)
        acc.push(possible)
      end
      acc
    end
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
