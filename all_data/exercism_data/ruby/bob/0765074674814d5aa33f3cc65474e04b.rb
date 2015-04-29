class Bob

  def hey(message)
    if is_empty?(message)
      "Fine. Be that way."
    elsif is_a_question?(message)
      "Sure."
    elsif is_yelling?(message)
      "Woah, chill out!"
    else
      "Whatever." 
    end
  end


  private

  def is_empty?(the_message)
    the_message.nil? || the_message == ""
  end

  def is_a_question?(the_message)
    the_message[-1] == "?"
  end

  def is_yelling?(the_message)
    the_message == the_message.upcase
  end

end
