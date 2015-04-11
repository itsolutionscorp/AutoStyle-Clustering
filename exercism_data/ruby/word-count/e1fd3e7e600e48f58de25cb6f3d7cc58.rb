class Phrase
  def initialize(sentence)
    @sentence = Sentence.new(sentence)
  end

  def word_count
    Words.new.tap do |words|
      @sentence.find_words do |word|
        words << word
      end
    end.count
  end

  private

  class Sentence < String
    def find_words(&block)
      downcase.scan(/\w+/, &block)
    end
  end

  class Words
    attr_reader :count

    def initialize
      @count = Hash.new(0)
    end

    def <<(word)
      count[word] += 1
    end
  end

end
