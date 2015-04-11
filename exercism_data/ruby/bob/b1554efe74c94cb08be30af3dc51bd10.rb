class Bob
  def hey(quote)
    if(quote == nil or quote == '' or quote =~ /\A\s*\z/)
      return "Fine. Be that way!"
    elsif(quote.upcase == quote)
      return "Woah, chill out!"
    elsif(quote =~ /.*[?]$/)
      return "Sure."
    else
      return "Whatever."
    end
  end
end
