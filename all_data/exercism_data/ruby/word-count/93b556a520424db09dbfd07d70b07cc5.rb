class Phrase
  def initialize(word)
    raise TypeError, 'Must be initialized with a string' unless word.is_a? String
    @word = word
  end

  def word_count
    normalize_words.each_with_object(Hash.new(0)) {|word, memo| memo[word] += 1}
  end

  private

  def normalize_words
    @word.downcase.scan(/\w+/)
  end
end
