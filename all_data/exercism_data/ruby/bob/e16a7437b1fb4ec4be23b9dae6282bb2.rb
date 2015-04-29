class Bob

  def hey(txt)
    case txt
    when /^[ ]*[^A-Za-z0-9!.]*$/
       'Fine. Be that way!'
    when /^[A-Z0-9, !%@#\$\^\*\(]*[!?]*$/
       'Woah, chill out!'
    when /[A-Za-z]+[.!]*$/
      'Whatever.'
    when /[0-9]*[?]+$/
       'Sure.'
    else
       '.'
    end
  end

end
