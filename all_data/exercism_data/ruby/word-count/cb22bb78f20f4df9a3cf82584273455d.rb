class Phrase
  def initialize(body)
    @body = body
  end

  def word_count
    list_of_words.each_with_object({}) do |word, results|
    increment_word_count results, word
    end
  end

  private

  def list_of_words
    @body.scan(/[a-zA-Z0-9]+/)
  end

  def increment_word_count(results, word)
    normalized_word = word.downcase
    results[normalized_word] = results[normalized_word].to_i + 1
  end
end
