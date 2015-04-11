class Phrase
  def initialize(raw_str)
    @raw_str = raw_str
  end

  def get_words
    @words = @raw_str.gsub(/[^a-zA-Z0-9']/,' ').split.map { |word| word.strip.downcase }
  end

  def count_words
    @words.each_with_object({}) { |word, h| h[word] = @words.count(word) }
  end

  def get_and_count_words
    get_words
    count_words
  end

  def word_count
    @word_count ||= get_and_count_words
  end
end
