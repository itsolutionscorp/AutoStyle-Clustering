class Phrase

  def initialize(input = '')
    @phrase = input
  end

  def word_count
    words = split_words
    distinct_words = get_distinct_words(words)
    count_distinct_words(distinct_words, words)
  end

  private

    def split_words
      @phrase.downcase.split(%r(\W+))
    end

    def get_distinct_words(words)
      words.uniq
    end

    def count_distinct_words(distinct_words, words)
      counts = Hash.new
      distinct_words.each do |word|
        counts[word] = words.count(word)
      end
      counts
    end
end
