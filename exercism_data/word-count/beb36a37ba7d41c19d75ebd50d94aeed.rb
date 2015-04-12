class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    @phrase.downcase.scan(/\b\w+\b/).each_with_object({}) do |word, counts|
      counts[word] = (counts[word] || 0) + 1
    end
  end
end