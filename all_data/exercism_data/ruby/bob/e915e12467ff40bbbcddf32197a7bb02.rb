class Bob

  def hey string
    return "Fine. Be that way!" if string =~ /\A\s*\z/ 
    return "Woah, chill out!" if string == string.upcase && string =~ /[a-zA-Z]/
    return "Sure." if string =~ /\?$/
    return "Whatever."
  end

end
