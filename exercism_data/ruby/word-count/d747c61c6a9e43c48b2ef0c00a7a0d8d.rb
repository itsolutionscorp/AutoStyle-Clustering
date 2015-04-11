class Words
  def initialize(phrase)
    @phrase = phrase
  end

  def count
    words_array.each_with_object(WordCounter.new) {|word, counter|
      counter.increment(word)
    }.counts
  end

  private

  def words_array
    phrase_without_punctuation.downcase.split
  end

  def phrase_without_punctuation
    @phrase.gsub(/[^a-z0-9]/i, ' ')
  end
end

class WordCounter
  def initialize
    @counter = Hash.new{|hash, key| hash[key] = 0}
  end

  def increment(word)
    @counter[word] += 1
  end

  def counts
    @counter
  end
end
