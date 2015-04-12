class Phrase

  def initialize(phrase)
    @phrase =  phrase
  end

  def word_count
    w_hash = {}   
    @phrase.scan(/[^\n ,][\w']+|\d+/im).each do |w| 
      w = w.downcase
      w_hash[w] = w_hash[w] == nil ? 1 : w_hash[w] + 1
    end
    w_hash
  end

end
