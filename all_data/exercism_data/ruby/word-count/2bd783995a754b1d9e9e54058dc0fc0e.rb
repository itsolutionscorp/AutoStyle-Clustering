class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    WordCounter.new(@phrase).count
  end

end

class WordCounter

  def initialize(phrase)
    @phrase = normalize(phrase)
  end

  def count
    counted_words = {}
    counted_words.default = 0

    find_words.each_with_object(counted_words) do |word, counter|
      counter[word] += 1
    end
  end

  private

  def find_words
    @phrase.scan(/\w+/)
  end

  def normalize(phrase)
    phrase.downcase
  end
end
