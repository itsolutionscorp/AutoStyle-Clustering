class Bob

  def hey(prompt)
    response = TeenageResponse.new(prompt)
    response.to_s
  end

  class TeenageResponse

    def initialize(prompt)
      @prompt = String(prompt)
    end

    def silence?
      @prompt.empty?
    end

    def question?
      @prompt.end_with?('?')
    end

    def yelling?
      @prompt.upcase == @prompt
    end

    def to_s
      if silence?
        "Fine. Be that way."
      elsif yelling?
        "Woah, chill out!"
      elsif question?
        "Sure."
      else
        "Whatever."
      end
    end

  end

end
