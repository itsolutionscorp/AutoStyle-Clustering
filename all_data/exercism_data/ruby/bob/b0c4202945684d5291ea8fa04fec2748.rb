class Bob 

  def hey (greeting) 
    if silent? greeting
      "Fine. Be that way."	
    elsif question? greeting
      "Sure."
    elsif loud? greeting
      "Woah, chill out!"
    else
      "Whatever."
    end
  end

  def silent? (greeting)
    greeting.nil? || greeting.empty?
  end

  def question? (greeting) 
    greeting.end_with?('?')
  end

  def loud? (greeting)
    greeting.upcase!.nil?
  end
end
