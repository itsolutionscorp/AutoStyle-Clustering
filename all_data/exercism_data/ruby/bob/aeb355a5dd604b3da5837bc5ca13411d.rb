class Bob
  def hey(telling)
    # Convert to a string and remove white spaces
    telling = telling.to_s.strip

    if telling.empty?
      'Fine. Be that way!'
    elsif telling == telling.upcase
      'Woah, chill out!'
    elsif telling[-1] == '?'
      'Sure.'
    else
      'Whatever.'
    end

  end
end
