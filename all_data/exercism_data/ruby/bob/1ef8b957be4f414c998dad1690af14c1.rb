class Bob
  def hey(saying)
    return silence_response if silent? saying
    return heated_response if yelling? saying
    return confirming_response if question? saying
    normal_response
  end

  private

  def normal_response
    "Whatever."
  end

  def confirming_response
    'Sure.' 
  end

  def heated_response
    'Woah, chill out!' 
  end

  def silence_response
    'Fine. Be that way.'
  end

  def silent?(saying)
    saying.nil? || saying.empty?
  end

  def yelling?(saying)
    saying == saying.upcase
  end

  def question?(saying)
    saying.end_with? '?'
  end
end
