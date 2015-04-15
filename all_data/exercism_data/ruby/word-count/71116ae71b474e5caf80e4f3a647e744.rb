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
    find_words.group_by { |word| word }.each_with_object({}) do |(word, occurances), counter|
      counter[word] = occurances.size
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
