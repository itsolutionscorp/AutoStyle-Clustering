class Bob
  def hey(quote)
    if quote.strip.empty?
      "Fine. Be that way!"
    elsif quote.upcase == quote
      "Woah, chill out!"
    elsif quote.end_with?("?")
      "Sure."
    else
      "Whatever."
    end
  end
end
