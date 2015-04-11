class Bob
  def hey(request)
    return 'Fine. Be that way!' if request.strip.length < 1
    return 'Woah, chill out!' if request.upcase == request
    return 'Sure.' if request[-1] == '?'
    'Whatever.'
  end
end
