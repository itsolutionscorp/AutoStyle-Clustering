class Phrase

  def initialize(phrase)
    phrase = PhraseParser.new(phrase)
    @words = phrase.extract_words
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

end


class PhraseParser
  def initialize(phrase)
  @phrase = phrase
  end

  def extract_words
    phrase.downcase.scan(/\w+/)
  end

  private

  def phrase
    @phrase
  end

end
