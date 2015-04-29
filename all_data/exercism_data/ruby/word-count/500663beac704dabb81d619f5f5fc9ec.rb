class Phrase
  def initialize phrase
    @phrase = phrase
  end

  def word_count
    words_and_occurences = unique_words.map do |word|
      [word, occurences(word)]
    end

    Hash[words_and_occurences]
  end

  private

  def unique_words
    words.uniq
  end

  def words
    @words ||= phrase_with_normalized_case.gsub(/[^a-z0-9, ]/, '').split(/[, ]+/)
  end

  def phrase_with_normalized_case
    @phrase.downcase
  end

  def occurences word
    words.count(word)
  end
end
