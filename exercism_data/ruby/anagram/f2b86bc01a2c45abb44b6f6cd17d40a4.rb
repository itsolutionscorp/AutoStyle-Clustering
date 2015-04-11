class Anagram
  attr_reader :canonical_word

  def initialize(word)
    @canonical_word = word.split('').sort
  end

  def match(examples)
    examples.select { |example| anagram?(example) }
  end

  private

  def anagram?(example)
    example.split('').sort == canonical_word
  end

end
