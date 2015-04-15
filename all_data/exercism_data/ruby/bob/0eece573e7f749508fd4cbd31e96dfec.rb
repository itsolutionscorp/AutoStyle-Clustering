class Bob
  def hey(something)
    # make  nil case not an option
    something ||= ''
    communication_type = :nothing
    case 
    when something.empty?
      communication_type = :nothing
    when something.upcase == something
      communication_type = :shouting
    when something.end_with?("?")
      communication_type = :question  
    when something.end_with?("." , "!")
      communication_type = :statement
    end
    respond_to communication_type
  end

  def respond_to(communication_type)
    case communication_type
    when :nothing
      "Fine. Be that way."
    when :shouting
      "Woah, chill out!"
    when :question
      "Sure."
    when :statement
      "Whatever."
    end
  end
end
