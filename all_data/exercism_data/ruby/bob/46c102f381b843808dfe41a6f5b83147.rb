class Bob
  def hey(input)
    Responder.new(input).response
  end

  class Responder
    attr_accessor :input

    def initialize(input)
      self.input = input
    end

    def input
      @input ||= ""
    end

    def response
      if input == ""
        "Fine. Be that way!"
      elsif input == input.upcase
        "Woah, chill out!"
      elsif input.end_with? "?"
        "Sure."
      else
        "Whatever."
      end
    end
  end
end
