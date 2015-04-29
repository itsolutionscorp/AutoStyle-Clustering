class Phrase
  def initialize(string)
    @string = string
  end

  def word_count
    WordCounter.count(words)
  end

  private
  def words
    @string.scan(/[[:alnum:]]+/)
  end

  module WordCounter
    def self.count(words)
      words.each_with_object(Hash.new(0)) do |word, hash|
        hash[normalize(word)] += 1
      end
    end

    private
    def self.normalize(word)
      word.downcase
    end
  end
end
