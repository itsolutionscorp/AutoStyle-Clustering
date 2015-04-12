class Phrase
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    parse(phrase)
  end

  private

  def parse(phrase)
    words = skip_non_words(get_words(phrase))
    count_ocurrences(words)
  end

  def get_words(phrase)
    phrase.strip.downcase.split(/( |,)/)
  end

  def skip_non_words(words)
    words.select { |w| w.match(/\w/) }
  end

  def count_ocurrences(words)
    counts = {}
    words.each do |w|
      w = remove_non_word_chars(w)
      counts[w] ||= 0
      counts[w] += 1
    end
    counts
  end

  def remove_non_word_chars(word)
    word.gsub(/\W/, '')
  end
end
