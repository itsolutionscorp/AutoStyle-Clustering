class Phrase
  def initialize(word)
    raise TypeError, 'Must be initialized with a string' unless word.is_a? String
    @word = word
    normalize_words
  end

  def word_count
    @word_list ||= @word.split.reduce(Hash.new(0)) {|memo, word| memo[word] += 1; memo}
  end

  private

  def normalize_words
    remove_punctuation
    @word.downcase!
  end

  def remove_punctuation
    @word.gsub!(/[!@#\$,&:^%]/, ' ')
  end
end
