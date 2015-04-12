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
      clean @input.downcase
    end

    def clean(string)
      string.scan word_regex
    end

    def word_regex
      /\w+/
    end

end
