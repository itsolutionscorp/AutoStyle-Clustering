class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    @count ||= count_words
  end

  private

    def count_words
      words.each_with_object(Hash.new(0)) do |word, counts|
        counts[word] += 1
      end
    end

    def words
      normalized.scan(/\w+/)
    end

    def normalized
      @phrase.downcase
    end

end
