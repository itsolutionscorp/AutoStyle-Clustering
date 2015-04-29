class Bob
  def hey(request)
    if blank?(request)
      'Fine. Be that way!'
    elsif shouting?(request)
      'Woah, chill out!'
    elsif question?(request)
        'Sure.'
    else
      'Whatever.'
    end
  end
  
  private
  
  def blank?(request)
    request == '' || request.match(/\A\s+\z/)
  end
  
  def shouting?(request)
    request.upcase == request
  end
  
  def question?(request)
     request.end_with? '?'
  end
end
