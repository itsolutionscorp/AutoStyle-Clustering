class Bob
  def hey(response)
    if response.strip.empty?
      'Fine. Be that way!'
    elsif response.upcase == response
      'Woah, chill out!'
    elsif response[-1] == "?"
      'Sure.'
    else
      'Whatever.'
    end
  end
end
