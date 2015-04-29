class Phrase
  def initialize(phrase)
    @words = extract_words(phrase)
  end

  def word_count
    @words.each_with_object(Hash.new(0)) do |word, counts| 
      counts[word] += 1
    end
  end

  private
    def extract_words(phrase)
      phrase.downcase.scan(/\w+/)
    end
end
