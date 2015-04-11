class Bob
  def hey req

    if /\A(?!Let's|let's)(.*\!|^[A-Z]+[A-Z\W]*)\z/ =~ req
      'Woah, chill out!'
    elsif /\A.*\?\z/ =~ req
      'Sure.'  
    elsif /\A\s*\z/ =~ req
      'Fine. Be that way!'
    else
      'Whatever.'
    
    end

  end

end
