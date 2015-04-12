class Phrase
  def initialize phrase
    @phrase = phrase.to_s
  end

  def word_count
    normalized = normalize_case @phrase
    clean = remove_non_word_chars normalized
    words = clean.split

    count_words words
  end

  private

  def normalize_case s
    s.downcase
  end

  def remove_non_word_chars s
    #\W+ match one or more non-word characters
    s.gsub(/\W+/, ' ')
  end

  def count_words words
    counts = Hash.new 0

    words.each { |w| counts[w] += 1 }

    counts
  end
end
