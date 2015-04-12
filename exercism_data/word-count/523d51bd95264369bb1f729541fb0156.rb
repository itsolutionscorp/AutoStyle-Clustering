class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    result = {}
    clean_phrase.split.each do |word|
      result[word] = result[word] ? result[word]+1 : 1
    end
    result
  end

  private

  def clean_phrase
    @phrase.downcase.gsub(/[^a-z0-9]/, ' ').gsub(/\s+/, ' ')
  end

end
