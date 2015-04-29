class Phrase
  attr_accessor :phrase
  def initialize(phrase)
    self.phrase = phrase
  end

  def word_count
    downcase_phrase!(phrase)
    sanitize_phrase!(phrase)
    words = split_words(phrase)
    counts = Hash.new
    words.each do |w|
      counts[w] = counts[w].to_i + 1
    end
    return counts
  end

private
  def downcase_phrase!(phrase)
    phrase.downcase!
  end

  def sanitize_phrase!(phrase)
    phrase.gsub!(/[^0-9A-Za-z ,]/, '')
    phrase.gsub!(/\s\s+/, ' ')
    phrase.gsub!(/\,\s/, ' ')
  end

  def split_words(phrase)
    phrase.split(/[ ,]/)
  end
end
