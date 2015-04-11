class Bob
  def hey request
    request = request.to_s.strip
    respond(request)
  end

  def respond request
    if request == ''
      'Fine. Be that way!'
    elsif shouting? request
      'Woah, chill out!'
    elsif question? request
      'Sure.'
    else
      'Whatever.'
    end
  end

  def shouting? request
    request == request.upcase
  end

  def question? request
    request[-1] == '?'
  end
end
