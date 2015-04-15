class Bob
  def hey(request)
    case
    when is_silence?(request)
      'Fine. Be that way!'
    when is_shouting?(request)
      'Woah, chill out!'
    when is_question?(request)
      'Sure.'
    else
      'Whatever.'
    end
  end

  def is_silence?(request)
    request.strip.empty?
  end

  def is_shouting?(request)
    request.upcase == request
  end

  def is_question?(request)
    request.end_with?('?')
  end
end
