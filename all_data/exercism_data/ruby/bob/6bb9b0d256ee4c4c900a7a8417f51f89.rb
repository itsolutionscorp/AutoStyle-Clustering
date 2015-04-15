class Bob
  def hey(greeting)
    if silent?(greeting)
      "Fine. Be that way!"
    elsif shout?(greeting)
      "Woah, chill out!" 
    elsif question?(greeting)
      "Sure."
    else 
      "Whatever."
    end
  end

  def silent?(greeting)
    greeting.nil? || greeting.strip.empty?
  end
   
  def shout?(greeting)
    ! greeting.match(/[a-z]/)
  end

  def question?(greeting)
    greeting.end_with?("?")
  end

end
