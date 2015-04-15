class Bob
  def hey(message)
    @message = message

    if silent?
      'Fine. Be that way.'
    elsif shouting?
      'Woah, chill out!' 
    elsif question?
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def question?
    /.*\?$/.match @message
  end

  def shouting?
    /^[[:upper:][:digit:][:punct:] ]+$/.match @message
  end

  def silent?
    @message.nil? || @message.empty?
  end
end
