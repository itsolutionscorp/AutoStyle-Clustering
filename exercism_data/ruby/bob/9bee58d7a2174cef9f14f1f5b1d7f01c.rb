class Bob
  def hey request
    request = request.to_s.strip
    respond(request)
  end

  def respond request
    if request == ''
      'Fine. Be that way!'
    elsif request == request.upcase
      'Woah, chill out!'
    elsif request[-1] == '?'
      'Sure.'
    else
      'Whatever.'
    end
  end
end
