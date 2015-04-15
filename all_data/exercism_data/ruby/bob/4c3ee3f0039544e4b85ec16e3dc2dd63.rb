class Bob
  def hey(input)
    if input[/\S/]
      if input[-1] == "?"
        return "Sure."
      elsif input.eql?(input.upcase)
        return "Woah, chill out!"
      else
        return "Whatever."
      end
    else
      return "Fine. Be that way!"
    end
  end
end
