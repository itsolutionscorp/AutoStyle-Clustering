class Phrase

  attr_reader :list

  ONLY_WORDS_AND_NUMBERS = %r{\w+}

  def initialize(words)
    @list = filtered_words(words)
  end

  def word_count
    unique_words.each_with_object({}) do |word, word_hash|
      word_hash[word] = list.count(word)
    end
  end

  private

    def unique_words
      list.uniq
    end

    def filtered_words(words)
      words.downcase.scan(ONLY_WORDS_AND_NUMBERS)
    end

end