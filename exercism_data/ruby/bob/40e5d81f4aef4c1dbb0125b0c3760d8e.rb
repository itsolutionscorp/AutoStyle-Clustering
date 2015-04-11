class Bob

  def hey(param)
    if param.strip == ""
      "Fine. Be that way!"
    elsif param.upcase == param && param.match(/[a-zA-Z]/)
      "Woah, chill out!"
    elsif param[-1] == "?"
      "Sure."
    else
      "Whatever."
    end
  end

end
