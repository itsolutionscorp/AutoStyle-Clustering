class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    counts = {}

    @phrase.downcase.scan(/[0-9a-z']+/).each do |word|
      counts[word] = (counts.include?(word) ? counts[word] + 1 : 1)
    end

    counts
  end
end
