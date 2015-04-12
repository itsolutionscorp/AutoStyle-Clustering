class Phrase

  def initialize phrase
    @phrase = phrase
    @words = cleaned_words
  end

  def word_count
    words.reduce({}) do |acc, w|
      w = w.downcase
      acc.merge(w => acc.fetch(w, 0) + 1)
    end
  end

  private
  attr_reader :phrase, :words

  def cleaned_words
    phrase.scan(/[\w']+/)
  end
end
