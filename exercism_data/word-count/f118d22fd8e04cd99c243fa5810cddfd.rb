class Phrase

  def initialize(phrase)
    @phrase =  phrase
  end

  # in case we want to know the original phrase after initialization
  def phrase
    @phrase
  end

  def word_count
    @phrase.downcase.scan(/[^\n ,][\w']+|\d+/im).inject({}) do |w_hash, w| 
      w_hash[w] = w_hash[w] == nil ? 1 : w_hash[w] + 1; w_hash
    end
  end

end
