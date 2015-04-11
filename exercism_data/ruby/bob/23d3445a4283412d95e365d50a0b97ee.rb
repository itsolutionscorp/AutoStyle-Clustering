class Bob
  def hey(string)
    if !string || string == ''
      'Fine. Be that way.'
    elsif string.upcase == string
      'Woah, chill out!'
    elsif string =~ /\?$/
      'Sure.'
    else
      'Whatever.'
    end
  end
end
