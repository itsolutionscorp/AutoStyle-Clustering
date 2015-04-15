begin
  class Bob
    def hey(input)
      request = Request.new(input)

      case
      when request.silence?
        "Fine. Be that way!"
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
      not @phrase or @phrase.strip.empty?
    end

    def shouting?
      @phrase and @phrase.upcase == @phrase
    end

    def question?
      @phrase and @phrase.end_with?("?")
    end
  end
end
