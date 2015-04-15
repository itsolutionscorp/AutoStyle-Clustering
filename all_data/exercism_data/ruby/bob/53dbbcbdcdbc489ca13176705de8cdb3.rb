class Bob

  def hey(convo)
    reply_to(convo)
  end

  def reply_to(convo)
    if convo.empty?
      'Fine, be that way.'
    elsif !convo.include?("?")
      respond(convo)
    elsif convo.include?("?")
      'Sure.'
    end
  end

  def respond(convo)
    lower_case = convo.upcase != convo
    up_case = convo.upcase == convo

    if lower_case
      'Whatever.'
    else
      'Woah, chill out!'
    end
  end
end
