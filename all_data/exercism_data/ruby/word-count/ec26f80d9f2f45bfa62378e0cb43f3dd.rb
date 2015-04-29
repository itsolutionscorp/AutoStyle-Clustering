class Phrase
  attr_reader :text
  def initialize(text, word_count_processor=WordCount)
    @text = text
    @word_count_processor = word_count_processor
  end

  def word_count
    @word_count ||= word_count_processor.call(self)
  end

  def text
    @text
  end

  private
  def word_count_processor
    @word_count_processor
  end
end

class WordCount
  def self.call(phrase)
    new(phrase).call
  end

  def initialize(phrase)
    @phrase = phrase
  end

  def call
    normalize(phrase.text).scan(/\w+/).each_with_object(Hash.new(0)) do |word, dict|
      dict[word] += 1
    end
  end

  private
  def normalize(text)
    text.downcase
  end

  def phrase
    @phrase
  end
end
