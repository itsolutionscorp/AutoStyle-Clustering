class Phrase
  def initialize(phrase)
    @phrase = prepare_phrase(phrase)
  end

  def prepare_phrase(phrase)
    phrase.downcase!
    phrase.scan(/\w+/)
  end

  def word_count
    @counted_words ||= count_words_once
  end

  private
    def count_words_once
      @phrase.each_with_object(Hash.new(0)) { |key, val| val[key] += 1 }
    end
end
