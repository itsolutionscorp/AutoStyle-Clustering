class Phrase
  def initialize(text)
    @text = text.to_s
    @words = @text.scan(/\w+/).map(&:downcase)
  end

  def word_count
    @words.each_with_object({}) do |word, counts|
      counts[word] = counts.fetch(word, 0) + 1
    end
  end
end
