class Bob

  def hey(input)
    # someone is shouting at me
    if ((input.upcase == input) and (input.match(/[a-zA-Z]/)))
      return 'Woah, chill out!'
    end
    # so, maybe you have some questions?
    if (input[input.length-1] == '?')
      return "Sure."
    end
    # you don't want to talk to me? OK.
    if  (not input.match(/\S/))
      return 'Fine. Be that way!'
    end
    #  this way or another, I really don't want to help you, because I'm too lazy ;)
    return "Whatever."
  end

end
