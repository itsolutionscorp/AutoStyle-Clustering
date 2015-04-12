class Phrase

  def initialize(phrase)
    @parser = PhraseParser.load(phrase)
  end

  def word_count
    words.inject({}) do |w_count, word| 
      w_count.merge({word => words.count(word)}) 
    end
  end

  private

  attr_reader :parser

  def words
    parser.extract_words
  end

end


class PhraseParser

  def self.load(phrase)
    @phrase = phrase
    self
  end

  def self.extract_words
    @phrase.downcase.scan(/\w+/)
  end

end
