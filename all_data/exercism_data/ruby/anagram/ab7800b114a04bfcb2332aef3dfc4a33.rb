class Anagram
  attr_reader :input

  def initialize(input)
    @input = prepare(input)
  end

  def match(words)
    words.select { |word| prepare(word) == input }
  end

  private

  def prepare(word)
    word.downcase.chars.sort
  end
end
