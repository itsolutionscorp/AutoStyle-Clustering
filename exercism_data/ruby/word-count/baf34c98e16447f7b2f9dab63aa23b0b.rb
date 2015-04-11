class Phrase

  def initialize(phrase)
    @words = phrase.split(/\W+/)
  end

  def word_count
    counts = Hash.new(0)
    @words.each do |word|
      counts[word.downcase] += 1
    end
    counts
  end

end
