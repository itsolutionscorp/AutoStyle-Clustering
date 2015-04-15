class Bob

  def hey(something_said)
    if they_are_silent?(something_said)
      "Fine. Be that way."    
    elsif they_are_shouting?(something_said)  
        "Woah, chill out!"
    elsif they_are_asking_a_question?(something_said)
        "Sure."
    else
        "Whatever."
    end
  end

  private
  
  def they_are_silent?(something_said)
    something_said.nil? || something_said.empty?      
  end
  
  def they_are_shouting?(something_said)
    something_said == something_said.upcase
  end
  
  def they_are_asking_a_question?(something_said)
    something_said.end_with?("?")
  end
  
end
