class Phrase
  def initialize(phrase)
    @words = phrase.gsub(/\W/, ' ').downcase.split
  end

  def word_count
    @words.reduce(Hash.new(0)) do |counts, word|
      counts[word] += 1
      counts
    end
  end
end
