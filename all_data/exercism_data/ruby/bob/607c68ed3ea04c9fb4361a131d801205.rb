class Bob
  def hey(input)
    if input =~ /[A-Z][A-Z][A-Z][A-Z]|[A-Z]!/
      return "Woah, chill out!"
    elsif input =~ /\?\Z/
      return "Sure."
    elsif input =~ /^\s*$/
      return "Fine. Be that way!"
    else
      return "Whatever."
    end
  end
end
