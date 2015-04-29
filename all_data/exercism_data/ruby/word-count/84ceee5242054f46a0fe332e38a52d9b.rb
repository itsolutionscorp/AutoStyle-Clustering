class Phrase
  def initialize(input)
    @input = input
  end

  def word_count
    words.each_with_object(Hash.new(0)) do |word, counter|
      counter[word] += 1
    end
  end

  private

    def words
      tokenize normalized_input
    end

    def normalized_input
      @input.downcase
    end

    def tokenize(string)
      string.scan word_regex
    end

    def word_regex
      /\w+/
    end

end
