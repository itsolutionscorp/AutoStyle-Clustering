class Phrase

  attr_reader :list

  ONLY_WORDS_AND_NUMBERS = %r{\w+}

  def initialize(words)
    @list = filtered_words(words)
  end

  def word_count
    counter = Hash.new { |h,k| h[k] = 0 }
    list.each_with_object(counter) do |word, counts|
      counts[word] += 1
    end
  end

  private

    def filtered_words(words)
      words.downcase.scan(ONLY_WORDS_AND_NUMBERS)
    end

end
