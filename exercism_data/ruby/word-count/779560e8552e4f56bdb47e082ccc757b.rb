class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    word_counts = {}
    split_into_words(@phrase).each do |word|
      increment_word_count(word_counts, word)
    end
    print_counts(word_counts)
  end

  private

  def increment_word_count(word_counts, word)
    word.downcase!
    if word_counts[word]
      word_counts[word] += 1
    else
      word_counts[word] = 1
    end
  end

  def split_into_words(phrase)
    phrase.scan(/[a-zA-Z0-9\']+/)
  end

  def print_counts(word_counts)
    word_counts.each do |pair|
      pair
    end
  end

end
