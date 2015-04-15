class Bob
  def hey(quote)
    if quote.nil? || quote.empty?
      'Fine. Be that way.'
    elsif quote.=~ /\?$/
      'Sure.'
    elsif quote == quote.upcase
      'Woah, chill out!'
    else
      'Whatever.'
    end
  end
end
