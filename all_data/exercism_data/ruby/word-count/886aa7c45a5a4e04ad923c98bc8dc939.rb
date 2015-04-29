class Phrase
  def initialize(words)
    @words = words
  end

  def word_count
    counts = {}
    @words.scan(/\w+/).each { |word|
      w = word.downcase
      counts[w] = counts[w].nil? ? 1 : counts[w] += 1
    }

    counts
  end
end
