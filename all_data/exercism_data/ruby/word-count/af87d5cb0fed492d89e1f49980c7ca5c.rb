class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    Words.add_all(find_words).count
  end

  private

  def find_words
    @phrase.downcase.scan(/\w+/)
  end

  class Words
    attr_reader :count

    def self.add_all(words)
      words.each_with_object(new) do |word, words_hash|
        words_hash << word
      end
    end

    def initialize
      @count = Hash.new(0)
    end

    def <<(word)
      count[word] += 1
    end
  end

end
