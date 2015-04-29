class Bob

  def hey(rant)

    if rant.to_s.strip == ''
      'Fine. Be that way!'

    elsif rant == rant.upcase
      'Woah, chill out!'

    elsif rant[-1, 1] == '?'
      'Sure.'

    else
      'Whatever.'
    end

  end

end
