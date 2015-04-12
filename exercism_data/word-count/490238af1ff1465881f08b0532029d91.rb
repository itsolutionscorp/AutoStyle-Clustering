class Phrase

  def initialize(input = '')
    @phrase = input
  end

  def word_count
    split_words
    get_distinct_words
    count_distinct_words
    return @counts
  end

  private

    def split_words
      @words = @phrase.downcase.split(%r(\W+))
    end

    def get_distinct_words
      @distinct_words = @words.uniq
    end

    def count_distinct_words
      @counts = Hash.new
      @distinct_words.each do |word|
        @counts[word] = @words.count word
      end
    end
end
