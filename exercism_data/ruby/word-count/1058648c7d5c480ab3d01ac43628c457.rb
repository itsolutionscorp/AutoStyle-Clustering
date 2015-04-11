class Phrase
  attr_reader :raw_str

  def initialize(raw_str)
    @raw_str = raw_str
  end

  def words
    @words ||= raw_str.gsub(/[^a-zA-Z0-9']/,' ').split.map { |word| word.strip.downcase }
  end

  def count_words
    words.each_with_object({}) { |word, h| h[word] = @words.count(word) }
  end

  def word_count
    @word_count ||= count_words
  end
end
