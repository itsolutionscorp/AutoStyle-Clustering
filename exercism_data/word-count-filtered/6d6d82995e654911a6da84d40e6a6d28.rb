class Phrase
  def initialize(words)
    @words = words
  end

  def word_count
    counts = Hash.new(0)
    @words.downcase.split(/\W+/).each do |word|
      counts[word] += 1
    end
    counts
  end
end
