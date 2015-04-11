class Bob

  RESPONSES = {
    question: "Sure.",
    yell:     "Whoa, chill out!",
    silence:  "Fine. Be that way!",
    default:  "Whatever."
  }

  private_constant :RESPONSES

  def hey(prompt)
    if prompt !~ /[^\s]/
      RESPONSES[:silence]
    elsif prompt.upcase == prompt && prompt =~ /[a-zA-Z]/
      RESPONSES[:yell]
    elsif prompt.end_with? "?"
      RESPONSES[:question]
    else
      RESPONSES[:default]
    end
  end

end
