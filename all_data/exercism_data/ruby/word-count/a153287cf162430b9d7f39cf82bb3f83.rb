class Phrase
  def initialize(word)
    raise TypeError, 'Must be initialized with a string' unless word.is_a? String
    @word = word
  end

  def word_count
    normalize_words.reduce(Hash.new(0)) {|memo, word| memo[word] += 1; memo}
  end

  private

  def normalize_words
    @word.downcase.scan(/\w+/)
  end
end
