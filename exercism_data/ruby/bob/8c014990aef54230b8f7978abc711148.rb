class Bob
  def hey(input)
    @input = input

    def not_yelling_or_questioning
      if @input =~ /[a-z]/ and @input[-1] != "?"
        "Whatever."
      else
        questioning
      end
    end

    def questioning
      if @input =~ /[a-z]/ and @input[-1] == "?"
        "Sure."
      else
        yelling
      end
    end

    def yelling
      if @input =~ /[A-Z]/
        "Woah, chill out!"
      end
    end

    unless @input == "" or @input == nil
      not_yelling_or_questioning
    else
      "Fine. Be that way."
    end
  end
end
