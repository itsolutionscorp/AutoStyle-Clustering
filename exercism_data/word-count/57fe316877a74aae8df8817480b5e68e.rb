class CaseInsensitiveTokenizer
  def initialize(phrase)
    @phrase = phrase
  end

  def tokens
    @phrase.downcase.scan /\w+/
  end

end

class Phrase
  def initialize(phrase)
    @words = CaseInsensitiveTokenizer.new(phrase).tokens
  end

  def word_count
    count @words
  end

  private

    def count(words)
      count = Hash.new(0)
      words.each { |word| count[word] = count[word] + 1 }
      count
    end

end
