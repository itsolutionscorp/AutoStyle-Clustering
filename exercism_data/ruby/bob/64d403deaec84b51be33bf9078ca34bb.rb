class Bob

  def hey(answer)
    case
    when answer =~ /[?]$/ && answer =~ /[[:lower:]]/
      'Sure.'
    when answer =~ /[^?]$/ && answer =~ /[[:lower:]]/
      'Whatever.'
    when answer =~ /[[:upper:]]+/
      'Woah, chill out!'
    else
      'Fine. Be that way!'
    end

  end

end
