class Bob

  def hey(question)
    case
    when question =~ /[?]$/ && question =~ /[[:lower:]]/
      'Sure.'
    when question =~ /[^?]$/ && question =~ /[[:lower:]]/
      'Whatever.'
    when question =~ /[[:upper:]]+/
      'Woah, chill out!'
    else
      'Fine. Be that way!'
    end
  end

end
