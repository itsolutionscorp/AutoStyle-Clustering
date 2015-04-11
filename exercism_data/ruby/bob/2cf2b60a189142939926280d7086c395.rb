class Bob
  def hey(input)
    @input = input

    def yelling
      if @input == @input.upcase
        true
      end
    end

    def questioning
      if @input.end_with? "?"
        true
      end
    end

    if @input == "" or @input == nil
      "Fine. Be that way."
    elsif yelling
      "Woah, chill out!"
    elsif questioning
      "Sure."
    else
      "Whatever."
    end

  end
end
