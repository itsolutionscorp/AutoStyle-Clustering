class Phrase
  def initialize(body)
    @body = body
  end

  def word_count
    normalized_words.each_with_object(hash_defaulting_to_zero) do |word, results|
    increment_word_count results, word
    end
  end

  private

  def normalized_words
    @body.downcase.scan(/[a-zA-Z0-9]+/)
  end

  def hash_defaulting_to_zero
    Hash.new(0)
  end

  def increment_word_count(results, word)
    results[word] = results[word] + 1
  end
end
