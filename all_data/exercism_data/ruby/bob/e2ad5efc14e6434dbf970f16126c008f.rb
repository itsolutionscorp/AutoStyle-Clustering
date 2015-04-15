class Bob
  def hey(saying)
    Retort.new(saying).to_s
  end

  class Retort
  
    def initialize(saying="")
      @saying = saying.to_s
    end
  
    def to_s
      return "Fine. Be that way." if silent?
      return "Sure." if question?
      return "Woah, chill out!" if shouting?
      "Whatever."
    end

    protected

    def silent?
      @saying.empty?
    end

    def question?
      @saying.end_with? "?"
    end

    def shouting?
      @saying == @saying.upcase
    end
  end
  
end
