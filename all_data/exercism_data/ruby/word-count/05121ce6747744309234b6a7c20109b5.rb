class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    set_counts(format_phrase)
  end

  def format_phrase
    @phrase.gsub(/[^0-9A-Za-z]/, ' ').split
  end

  def set_counts(words)
    counts = Hash.new(0)
    words.each{ |word| counts[word.downcase] += 1 }
    counts
  end
end
