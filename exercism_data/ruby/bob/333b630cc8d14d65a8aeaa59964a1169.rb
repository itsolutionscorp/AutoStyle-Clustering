class Bob

  def hey(response_for)
    return 'Fine. Be that way.' if nothing?(response_for)
    return 'Woah, chill out!' if shouting?(response_for)
    return 'Sure.' if question?(response_for)
    "Whatever."
  end
  
  private
  def nothing?(response_for)
     !response_for || response_for.strip.empty? 
  end

  def shouting?(response_for)
    response_for.upcase == response_for
  end

  def question?(response_for)
    response_for.end_with?("?")
  end
  
end
