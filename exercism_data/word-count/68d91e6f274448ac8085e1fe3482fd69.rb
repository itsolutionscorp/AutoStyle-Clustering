class Phrase
  def initialize(words)
    @words = words
  end

  def word_count
    @words.downcase.scan(/\w+/).each.each_with_object({}) do |word, counts|
      counts[word] = counts[word].nil? ? 1 : counts[word] += 1
    end
  end
end
