class Bob
  def hey(message)
    @message = message

    if message.nil? or message.empty?
      'Fine. Be that way.'
    elsif shouting?
      'Woah, chill out!' 
    elsif question?
      'Sure.'
    else
      'Whatever.'
    end
  end

  def question?
    /.*\?$/.match @message
  end

  def shouting?
    /^[[:upper:][:digit:][:punct:] ]+$/.match @message
  end
end
