begin
  class Bob
    def hey(input)
      request = Request(input)

      case
      when request.silence?
        "Fine, be that way."
      when request.shouting?
        "Woah, chill out!"
      when request.question?
        "Sure."
      else
        "Whatever."
      end
    end
  end
  
  class Request
    def initialize(phrase)
      @phrase = phrase
    end

    def silence?
      @phrase.empty?
    end

    def shouting?
      @phrase.upcase == @phrase
    end

    def question?
      @phrase.end_with?("?")
    end
  end
end
