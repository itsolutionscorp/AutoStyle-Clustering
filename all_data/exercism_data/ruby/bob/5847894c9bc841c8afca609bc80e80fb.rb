class Bob
  class Responder
    attr_reader :string

    def initialize(string)
      @string = string
    end

    def response
      if yell?
        "Woah, chill out!"
      elsif question?
        "Sure."
      elsif silence?
        "Fine. Be that way!"
      else
        "Whatever."
      end
    end

    private

    def question?
      string =~ /\?\Z/
    end

    def yell?
      string =~ /[A-Z]+/ &&
      string == string.upcase
    end

    def silence?
      string =~ /\A\s*\Z/
    end
  end

  def hey(string)
    Responder.new(string).response
  end
end
