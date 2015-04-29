class Phrase

  def initialize(phrase)
    @words = extract_words_from(phrase)
  end

  def word_count
    words.inject({}) do |wco, word| 
      wco.merge({word => words.count(word)}) 
    end
  end

  private

  def words
    @words
  end

  def extract_words_from(phrase)
    phrase.downcase.scan(/\w+/)
  end
 
end
