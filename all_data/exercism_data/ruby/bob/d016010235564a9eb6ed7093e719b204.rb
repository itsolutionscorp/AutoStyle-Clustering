class Bob
  def hey(string)
    case
    when silent?(string)
      "Fine. Be that way!"
    when shouting?(string)
      "Woah, chill out!"
    when questioning?(string)
      "Sure."
    else
      "Whatever."
    end
  end

  private

  def silent?(string)
    string.strip.empty?
  end

  def shouting?(string)
    string.upcase == string
  end

  def questioning?(string)
    string.end_with?("?")
  end
end
