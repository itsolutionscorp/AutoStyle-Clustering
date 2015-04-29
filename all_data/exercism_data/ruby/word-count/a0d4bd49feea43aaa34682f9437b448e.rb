class Phrase

  def initialize(phrase)
    @phrase = PhraseParser.new(phrase)
  end

  def word_count
    words.uniq.each_with_object({}) do |word, counts| 
      counts[word] = words.count(word)  
    end
  end

  private

  attr_reader :phrase

  def words
    phrase.tokenize
  end

end


class PhraseParser

  def initialize(phrase)
    @phrase = phrase
  end

  def tokenize
    @phrase.downcase.scan(/\w+/)
  end

end
