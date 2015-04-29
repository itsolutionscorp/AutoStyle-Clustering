class Phrase

  def initialize(phrase)
    @phrase = phrase
    @parser = PhraseParser
  end

  def word_count
    words.inject({}) do |w_count, word| 
      w_count.merge({word => words.count(word)}) 
    end
  end

  private

  attr_reader :phrase

  def words
    @parser.new(phrase).extract_words
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
