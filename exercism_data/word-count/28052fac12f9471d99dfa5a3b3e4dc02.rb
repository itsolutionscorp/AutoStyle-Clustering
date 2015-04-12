class Phrase
  def initialize(phrase, word_count_processor=WordCount)
    @phrase = phrase
    @word_count_processor = word_count_processor
  end

  def word_count
    @word_count ||= word_count_processor.new(phrase).call
  end

  private
  def word_count_processor
    @word_count_processor
  end

  def phrase
    @phrase
  end
end

class WordCount
  def initialize(phrase)
    @phrase = phrase
  end

  def call
    normalize(phrase).scan(/\w+/).each_with_object(Hash.new(0)) do |word, dict|
      dict[word] += 1
    end
  end

  private
  def normalize(phrase)
    phrase.downcase
  end

  def phrase
    @phrase
  end
end
