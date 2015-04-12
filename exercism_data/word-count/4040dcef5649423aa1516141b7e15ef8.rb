class Phrase
  attr_reader :words

  def initialize phrase
    @words = cleaned_words phrase
  end

  def word_count
    words.reduce({}) do |acc, w|
      w = w.downcase
      current_count = acc[w] || 0
      acc[w] = current_count + 1
      acc
    end
  end

  private

  def cleaned_words phrase
    clean_phrase(phrase).split
  end

  def clean_phrase phrase
    phrase.gsub(/[^\w']/,' ')
  end
end
