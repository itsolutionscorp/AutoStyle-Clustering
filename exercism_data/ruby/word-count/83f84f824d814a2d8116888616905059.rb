class Phrase
  def initialize(phrase)
    @words = WordCollection.new(phrase)
  end

  def word_count
    words.counts
  end

  attr_reader :words
end

class WordCollection 
  def initialize(phrase)
    @words = map_to_words phrase
  end

  def counts
    tally_count_of words
  end

  private
    attr_reader :words

    def map_to_words(phrase)
      phrase.scan(/\w+/).map {|w| w.downcase}
    end

    def tally_count_of (words)
      words.reduce(Hash.new(0)) {|counts, word| counts.update(word => counts[word] += 1)}
    end
end
