class Bob
  def hey(message)
    if shouting?(message)
      "Woah, chill out!"
    elsif question?(message)
      "Sure."
    elsif meaningless?(message)
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end
  
  def shouting?(message)
    (
      !empty?(message) &&
      contains_an_upper_case_letter?(message) &&
      !contains_a_lower_case_letter?(message)
    )
  end
  
  def contains_an_upper_case_letter?(message)
    (message.index(/[A-Z]/) != nil)
  end
  
  def contains_a_lower_case_letter?(message)
    (message.index(/[a-z]/) != nil)
  end
  
  def question?(message)
    message.end_with?("?")
  end
  
  def meaningless?(message)
    empty?(message)
  end
  
  def empty?(message)
    message.strip.empty?
  end
end
