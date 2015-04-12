class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words = split_phrase

    count_words(words)
  end

  private
    def split_phrase
      @phrase.downcase.scan(/\w+/)
    end

  def count_words(words)
    results = Hash.new(0)
    words.each do |word|
      results[word] += 1
    end
    results
  end
end
