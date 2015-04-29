class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    symbol_to_space
    strip_non_word

    counts = {}
    @phrase.split(/\s+/).each do |w|
      word = w.downcase
      counts[word] = 0 unless counts.include?(word)
      counts[word] += 1
    end
    counts
  end

  private

  def symbol_to_space
    @phrase.gsub!(/[,:\.]/, ' ')
  end

  def strip_non_word
    @phrase.gsub!(/[^a-zA-Z1-9' ]/, '')
  end
end
