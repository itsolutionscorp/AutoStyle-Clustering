class Bob
  def hey(input)
    # if Bob is not shouted at or asked a question, he doesn't care
    if input =~ /[a-z]/ and input[-1] != "?"
      "Whatever."
    # if he's asked a question, he says 'sure'
    elsif input =~ /[a-z]/ and input[-1] == "?"
      "Sure."
    # If he's shouted at, he says 'chill out!'
    elsif input =~ /[A-Z]/
      "Woah, chill out!"
    # with nil or an empty string, Bob just says 'Fine'
    else
      "Fine. Be that way."
    end
  end
end
