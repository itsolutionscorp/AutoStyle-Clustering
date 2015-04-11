class Bob
  def hey(request)
    last_char = request[-1]

    if request == '' || request.match(/\A\s+\z/)
      'Fine. Be that way!'
    elsif request.upcase == request
      'Woah, chill out!'
    elsif last_char == '?'
        'Sure.'
    else  
      'Whatever.'
    end
  end
end
