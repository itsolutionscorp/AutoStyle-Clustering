class Bob

  def hey(phrase = "")
    phrase = phrase || ""
    responder(phrase)
  end

  private
    def responder(phrase)
      if quiet?(phrase)
        'Fine. Be that way.'
      elsif yelling?(phrase)
        'Woah, chill out!'
      elsif question?(phrase)
        'Sure.'  
      elsif statement?(phrase) 
        'Whatever.'
      end
    end

    def quiet?(phrase)
      phrase.empty? 
    end

    def question?(phrase)
      phrase.end_with?("?")
    end

    def yelling?(phrase)
      phrase == phrase.upcase
    end

    def statement?(phrase)
      phrase
    end

end
