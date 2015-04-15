class Bob
  def hey(str)
    return "Fine. Be that way!" if str.strip == ""
    return "Woah, chill out!"   if str.count("A-Z") > 0 and str.count("a-z") == 0
    return "Sure."              if str.end_with? '?'
    return "Whatever."
  end
end
