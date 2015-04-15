class Anagram
  def initialize(input)
    @input = input.strip
  end

  def match(candidates)
    candidates.select { |candidate|
      !identical?(input,candidate) && anagram?(input,candidate)
    }
  end

  private

  attr_reader :input

  def identical?(word,other_word)
    word.downcase == other_word.downcase
  end

  def anagram?(word,other_word)
    normalize(word) == normalize(other_word)
  end

  def normalize(input_word)
    input_word.downcase.split("").sort.join
  end
end
