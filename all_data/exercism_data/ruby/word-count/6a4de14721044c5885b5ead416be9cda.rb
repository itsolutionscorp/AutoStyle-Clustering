class Phrase
  include Enumerable
  attr_reader :input

  def initialize(input)
    @input = input
  end

  def word_count
    self.to_a.uniq.each_with_object(Hash.new { |hash, key| hash[key] = 0 }) { |word, h| h[word] = self.count(word) }
  end

  def each(&block)
    tokenized.each(&block)
  end

  def tokenized
    @tokenized ||= self.input.downcase.gsub(/[^a-zA-Z0-9_\s]+/, ' ').split(/\s+/)
  end
end
