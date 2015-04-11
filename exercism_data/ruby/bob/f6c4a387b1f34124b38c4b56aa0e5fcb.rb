class Bob
  def hey(greeting)
    if silent? greeting
      "Fine. Be that way!"
    elsif shouted? greeting
      "Woah, chill out!"
    elsif questioned? greeting
      "Sure."
    else
      "Whatever."
    end
  end

private
  def questioned?(greeting)
    greeting.end_with? "?"
  end

  def silent?(greeting)
    greeting.lstrip.empty?
  end

  def shouted?(greeting)
    greeting.upcase == greeting
  end
end
