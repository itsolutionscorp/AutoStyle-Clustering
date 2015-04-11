class Bob
  def hey(string)
    speech = Speech.new(string)
    if speech.silence?
      "Fine. Be that way!"
    elsif speech.shouting?
      "Woah, chill out!"
    elsif speech.question?
      "Sure."
    else
      "Whatever."
    end
  end
  
  class Speech
    attr_reader :string
    private :string
    
    def initialize(string)
      @string = string
    end
    
    def shouting?
      string.upcase == string
    end
  
    def question?
      string.end_with?('?')
    end
  
    def silence?
      string.strip == ""
    end
  end
end
