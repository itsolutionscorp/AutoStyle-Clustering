class Phrase
  def initialize(input)
    @input = input
  end

  def word_count
    tokenized.each_with_object(Hash.new(0)) { |word, h| h[word] += 1 }
  end
  
  protected

  def tokenized
    @input.downcase.split(/\W+/)
  end
end
