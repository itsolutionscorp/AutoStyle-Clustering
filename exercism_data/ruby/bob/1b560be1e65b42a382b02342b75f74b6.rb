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
      @phrase = phrase.to_s.strip
    end

    def silence?
      @phrase.empty?
    end

    def shouting?
      not(@phrase.empty?) && @phrase.upcase == @phrase
    end

    def question?
      @phrase.end_with?("?")
    end
  end
end
