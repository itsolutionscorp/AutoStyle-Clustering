class Phrase

  def initialize(phrase)
    @words = parse_words(phrase)
  end

  def word_count
    @words.reduce({}) do |counts, word|
      if counts.has_key?(word)
        counts[word] += 1
      else
        counts[word] = 1
      end
      counts
    end
  end

  private

  WORD_DELIMITER = /[ ,]/

  def parse_words(phrase)
    words = phrase.downcase.split(WORD_DELIMITER)
    remove_non_word_characters(words)
  end

  def remove_non_word_characters(words)
    words.map { |word| word.gsub(/\W/, "") }.reject(&:empty?)
  end
end
