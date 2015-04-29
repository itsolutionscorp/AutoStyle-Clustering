class Bob

  def hey(value)
    value = value.to_s.strip

    if value.length == 0
      'Fine. Be that way!'
    elsif value == value.upcase
      'Woah, chill out!'
    elsif value.end_with? "?"
      'Sure.'
     else
      'Whatever.'
    end
  end

end
