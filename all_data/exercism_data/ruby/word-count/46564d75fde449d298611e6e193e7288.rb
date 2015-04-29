class Phrase
  def initialize(phrase)
    @phrase = phrase
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
      prepare_phrase(@phrase).each_with_object(Hash.new(0)) { |key, hash| hash[key] += 1 }
    end
end
