class Bob

  def hey(arg)
    if silent?(arg)
      "Fine. Be that way!"
    elsif loud?(arg) 
      "Woah, chill out!"
    elsif question?(arg)
      "Sure."
    else
      "Whatever."
    end
  end

  private

  def silent? arg
    arg.strip == ""
  end

  def loud? arg
    arg == arg.upcase
  end

  def question? arg
    arg.end_with? "?"
  end

end
