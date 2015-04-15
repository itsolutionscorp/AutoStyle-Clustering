class Bob

  def hey(phrase)
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
      else
        'Whatever.'
      end
    end

    def quiet?(phrase)
      phrase.to_s.empty? 
    end

    def yelling?(phrase)
      phrase == phrase.upcase
    end

    def question?(phrase)
      phrase.end_with?("?")
    end

end
