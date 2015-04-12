class Phrase

  attr_reader :list

  ONLY_WORDS_AND_NUMBERS = %r{[a-z0-9]+}

  def initialize(words)
    @list = filtered_words(words)
  end

  def word_count
    list.each_with_object({}) do |word, word_hash|
      word_hash[word] = count_of_words(word)
    end
  end

  private

    def filtered_words(words)
      words.downcase.scan(ONLY_WORDS_AND_NUMBERS)
    end

    def count_of_words(word)
      list.count { |w| word == w }
    end

end
