class Anagram

  attr_reader :word, :original

  def initialize(word)
    @original = word
    @word = normalize(word)
  end

  def match(words)
    words.select do |input|
      original.downcase != input.downcase &&
      word == normalize(input)
    end
  end

  def normalize(word)
    word.downcase.split('').sort.join
  end

end
