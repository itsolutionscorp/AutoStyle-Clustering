class Phrase
  attr_reader :word_count

  def initialize(phrase)
    @phrase = phrase
    @word_count = {}
    count_words
  end

  private

  def count_words
    sanitized_phrase.split(" ").each do |word|
      @word_count[word] = 0 if @word_count[word].nil?
      @word_count[word] += 1
    end
  end

  def sanitized_phrase
    @phrase.downcase.gsub(",", " ").gsub(/[^0-9a-z ]/, '')
  end
end
