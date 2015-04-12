class Phrase
  def initialize(text)
    @text = text.to_s
    @words = @text.scan(/\w+/).map(&:downcase)
  end

  def word_count
    @words.reduce({}) do |counts, word|
      counts[word] = counts.fetch(word, 0) + 1
      counts
    end
  end
end
