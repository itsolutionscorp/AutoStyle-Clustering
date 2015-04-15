class Phrase
  def initialize(input)
    @words = normalize(input)
  end

  def word_count
    @words.each_with_object(Hash.new(0)) do |word, counter|
      counter[word] += 1
    end
  end

  private

    def normalize(input)
      clean input.downcase
    end

    def clean(string)
      results = string.scan word_regex
      results.flatten
    end

    def word_regex
      /(\w+)/
    end

end
