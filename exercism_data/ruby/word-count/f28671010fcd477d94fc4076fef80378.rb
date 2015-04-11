class Phrase

  def initialize(string)
    @phrase = string		
  end

  def word_count
    @phrase.downcase.scan(/[\w']+/).reduce({}) do | acc, item |
	  acc[item] = acc.fetch(item, 0) + 1 	
	  acc
    end	
  end

end
