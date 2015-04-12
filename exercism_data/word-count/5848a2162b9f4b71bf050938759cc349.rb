class Phrase
  def initialize phrase
    self.phrase = phrase
  end

  def word_count
    @word_count ||= sanitized_phrase.each.with_object(Hash.new 0) do |word, word_count|
      word_count[word] += 1
    end
  end

  private

  def sanitized_phrase
    phrase.downcase.split(/\W+/)
  end

  attr_accessor :phrase
  attr_writer :word_count
end
