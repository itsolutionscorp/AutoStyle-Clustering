class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    word_counts = {}
    split_into_words(@phrase).each_with_object(word_counts) do |word, counts|
      counts[word] ||= 0
        counts[word] += 1
    end
    print_counts(word_counts)
  end

  private

  def split_into_words(phrase)
    phrase.downcase.scan(/[\w\']+/)
  end

  def print_counts(word_counts)
    word_counts.each do |pair|
      pair
    end
  end

end
