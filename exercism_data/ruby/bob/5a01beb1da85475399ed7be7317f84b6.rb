class Bob
  def hey( saying )
    Retort.new(saying).to_s
  end

  class Retort
  
    def initialize( saying="")
      @saying = saying.to_s
    end
  
    def to_s
      if silent?
        "Fine. Be that way."
      elsif question?
        "Sure."
      elsif shouting?
        "Woah, chill out!"
      else
        "Whatever."
      end
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
