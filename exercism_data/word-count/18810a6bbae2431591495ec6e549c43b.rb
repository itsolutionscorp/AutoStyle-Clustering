class Phrase
  def initialize(words)
    @words = words
  end

  def word_count
    sanitized_words.each_with_object({}) do |word, h|
      h[word] = (h[word] || 0) + 1
    end
  end

  private

  def sanitized_words
    @words
      .downcase
      .tr(',', ' ')
      .gsub(/[^a-z0-9'\s]/, '')
      .split
  end
end
