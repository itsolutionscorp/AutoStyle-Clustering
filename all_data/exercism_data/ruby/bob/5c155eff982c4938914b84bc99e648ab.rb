class Bob
  def hey(request)
    case
    when silence?(request)
      'Fine. Be that way!'
    when shouting?(request)
      'Woah, chill out!'
    when question?(request)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def silence?(request)
    request.strip.empty?
  end

  def shouting?(request)
    request.upcase == request
  end

  def question?(request)
    request.end_with?('?')
  end
end
