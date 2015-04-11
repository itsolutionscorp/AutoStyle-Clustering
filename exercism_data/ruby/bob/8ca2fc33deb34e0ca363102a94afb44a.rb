class Bob

  def hey(string)
    if string  == string.upcase
      'Woah, chill out!'
    elsif string.empty?
      'Fine. Be that way!'
    elsif string.slice(-1,1) == "?"
      'Sure.'
    else
      'Whatever.'
    end

  end
end
