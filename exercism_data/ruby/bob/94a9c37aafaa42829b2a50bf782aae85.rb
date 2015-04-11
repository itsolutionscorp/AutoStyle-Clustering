class Bob

  def hey ( msg )
    case msg
      when capitals?( msg )
        "Woah, chill out!"
      when question?( msg )
        "Sure."
      when question_numeric?( msg )
        "Sure."
      when numbers_only?( msg )
        "Whatever."
      when silence?( msg )
        msg
      else "Whatever."
    end
  end

  def capitals?( msg )
   if ((msg == msg.upcase) and msg.include? "!")
     msg
   elsif ((msg == msg.upcase) and msg.include? "?")
     msg
   elsif msg == msg.upcase and msg =~ /^[A-Z]+/
     msg
   end
  end

  def question?( msg )
    if msg.include? "?" and msg.reverse[0,1] == "?"
      if msg.include? "fat?"
        msg
      else
        false
      end
    elsif msg.include? "Sure."
      msg
    end
  end

  def question_numeric?( msg )
    if msg.include? "?" and msg.reverse[0,1] == "?"
      if msg =~ /^[a-zA-Z0-9]+/
        msg
      end
    else
      false
    end
  end

  def numbers_only?( msg )
    if msg =~ /^[0-9]+$/
      msg
    else
      false
    end
  end

  def silence?( msg )
    if msg.include? "Fine"
      msg
    end
  end

end
