class Bob
  def hey string
    if string.strip.empty?
      return "Fine. Be that way!"
    elsif string.chop == string.chop.upcase
      return "Woah, chill out!"
    elsif string.split('').last == "?"
      return "Sure."
    else
      return "Whatever."
    end
  end
end
