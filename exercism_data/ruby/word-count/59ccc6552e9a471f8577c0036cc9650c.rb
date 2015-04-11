class Phrase
  attr_reader :input

  def initialize(input)
    @input = input
  end

  def word_count
    self.tokenized.each_with_object(Hash.new(0)) { |word, h| h[word] += 1 }
  end

  def tokenized
    @tokenized ||= self.input.downcase.split(/\W+/)
  end
end
