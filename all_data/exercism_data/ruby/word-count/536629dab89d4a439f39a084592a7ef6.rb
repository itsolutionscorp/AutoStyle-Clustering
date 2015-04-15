class Phrase
  attr_reader :input
  attr_reader :word_counts

  def initialize(input)
    @input = input
  end

  def word_count
    @word_count ||= process_input
  end

  private

  def process_input
    words = {}
    word_parts.each do |word|
      words[word] ||= 0
      words[word] += 1
    end
    words
  end

  def word_parts
    parts = remove_punctuation(input).split(/[\s,]/)
    parts.select {|part| part.strip.length > 0 }.map {|part| part.downcase }
  end

  def remove_punctuation(text)
    text.gsub /[^\w\d\s,]/, ""
  end
end
