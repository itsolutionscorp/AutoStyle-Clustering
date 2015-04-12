class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    Scorer.tally(words)
  end

  def words
    @phrase.downcase.scan(/\w+/)
  end
end

module Scorer
  def self.tally(list)
    list.each_with_object(Hash.new(0)) do |member, tally|
      tally[member] += 1
    end
  end
end
