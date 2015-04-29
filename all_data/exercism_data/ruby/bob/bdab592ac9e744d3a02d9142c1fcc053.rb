class Bob
  def hey(text)
    if text == "" || text == nil || text == "    "
      response = 'Fine. Be that way!'
    elsif text == text.upcase
      response = 'Woah, chill out!'
    elsif text[-1,1] == "?"
      response = 'Sure.'
    else
      response = 'Whatever.'
    end
    return response
  end
end
