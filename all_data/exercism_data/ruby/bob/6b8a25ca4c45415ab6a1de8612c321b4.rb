class Bob
  def hey(request)
    case
    when request.strip.length < 1
      'Fine. Be that way!'
    when request.upcase == request
      'Woah, chill out!'
    when request[-1] == '?'
      'Sure.'
    else
      'Whatever.'
    end
  end
end
