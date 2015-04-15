class Bob
  def hey(input)
    @input = input

    def yelling?
      @input == @input.upcase
    end

    def questioning?
      @input.end_with? "?"
    end

    def nothing?
      @input == "" or @input == nil
    end

    if nothing?
      "Fine. Be that way."
    elsif yelling?
      "Woah, chill out!"
    elsif questioning?
      "Sure."
    else
      "Whatever."
    end

  end
end
