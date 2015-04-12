class Phrase
  def initialize(phrase)
    @phrase = words
  end

  def word_count
    @phrase.downcase
          .scan(/\w+/)
          .each
          .each_with_object({}) do |word, counts|
      counts[word] = counts[word].nil? ? 1 : counts[word] += 1
    end
  end
end
