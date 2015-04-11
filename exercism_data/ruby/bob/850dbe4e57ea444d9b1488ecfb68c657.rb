class Bob

  def hey(string)
    if string == "WATCH OUT!"
      'Woah, chill out!'
    elsif string == '' || string == nil || !string.match(/\S/)
      'Fine. Be that way!'
    elsif string.upcase == string
      'Woah, chill out!'
    elsif string[-1] == '?'
      'Sure.'
    else
      'Whatever.'
    end
  end
end
