class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words.inject(Hash.new(0)) do |word_counts, word|
      word_counts[word] += 1
      word_counts
    end
  end

  private

  def phrase
    @phrase
  end

  def words
    @words ||= split_on_whitespace(sanitized_phrase)
  end

  def split_on_whitespace(string_with_whitespace)
    string_with_whitespace.split(/\s+/)
  end

  def sanitized_phrase
    whitelist_characters(phrase.to_s.downcase)
  end

  def whitelist_characters(string_with_garbage)
    string_with_garbage.gsub(/[^\s\d\w]/, '')
  end
end
