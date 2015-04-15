class Phrase
  include Enumerable
  attr_reader :input

  def initialize(input)
    @input = input
  end

  def word_count
    self.to_a.each_with_object(Hash.new(0)) { |word, h| h[word] += 1 }
  end

  def each(&block)
    tokenized.each(&block)
  end

  def tokenized
    @tokenized ||= self.input.downcase.split(/\W+/)
  end
end
