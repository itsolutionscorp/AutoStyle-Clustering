class Phrase

  def initialize(phrase)
    @phrase = PhraseParser.new(phrase)
  end

  def word_count
    words.inject({}) do |w_count, word| 
      w_count.merge({word => words.count(word)}) 
    end
  end

  private

  attr_reader :phrase

  def words
    phrase.extract_words
  end

end


class PhraseParser

  def initialize(phrase)
    @phrase = phrase
    self
  end

  def extract_words
    @phrase.downcase.scan(/\w+/)
  end

end
