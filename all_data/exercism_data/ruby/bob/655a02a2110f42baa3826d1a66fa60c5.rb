class Bob
  def hey text
    if text.strip == ''
      'Fine. Be that way!'
    elsif text.upcase == text
      'Woah, chill out!'
    elsif text[-1] == '?'
      'Sure.'
    else
      'Whatever.'
    end
  end
end
