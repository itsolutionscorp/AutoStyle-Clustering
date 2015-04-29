class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    WordCounter.word_count(@phrase)
  end
end

module WordCounter
  def self.word_count(phrase)
    count(words(phrase))
  end

  def self.words(phrase)
    phrase.downcase.scan(/\w+/)
  end

  def self.count(words)
    words.each_with_object(Hash.new(0)) { |word, counts|
      counts[word] += 1
    }
  end
end
