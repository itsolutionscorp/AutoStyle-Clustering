class Anagram
  attr_reader :word
  def initialize(word)
    @word = normalize(word)
  end

  def match(input)
    input.select { |candidate| anagram?(candidate) }
  end

  private
    def anagram?(candidate)
      normalize(candidate) == word
    end

    def normalize(string)
      string.downcase.chars.sort
    end
end
