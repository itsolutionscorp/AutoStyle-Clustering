class Bob 

  def hey (greeting) 
    if is_silence greeting
      "Fine. Be that way."	
    elsif is_question greeting
      "Sure."
    elsif is_loud greeting
      "Woah, chill out!"
    else
      "Whatever."
    end
  end

  def is_silence (greeting)
    if greeting.nil? or greeting.empty?
      true
    else
      false
    end
  end

  def is_question (greeting) 
    if greeting.end_with?('?')
      true
    else
      false
    end
  end

  def is_loud (greeting)
    if greeting.upcase!.nil?
      return true
    end

    return false
  end
end
