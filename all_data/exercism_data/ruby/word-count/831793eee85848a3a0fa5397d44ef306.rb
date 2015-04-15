class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    WordCounter.new(words).count
  end

  private

  def words
    @phrase.scan(/\w+/)
  end
end


class WordCounter
  def initialize(words)
    @words = words
  end

  def count
    @words.each_with_object(Hash.new(0)) do |word, counts|
      counts[normalize(word)] += 1
    end
  end

  private

  def normalize(word)
    word.downcase
  end
end
