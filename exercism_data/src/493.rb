class Phrase

  def initialize(phrase)
    @words = phrase.split(/\W+/)
  end

  def word_count
    @words.inject(Hash.new(0)) do |counts, word|
      counts[word.downcase] += 1
      counts
    end
  end
end
