class Bob

def hey(message)
  return 'Fine. Be that way!' if nothing? message
  return 'Woah, chill out!' if yelling? message
  return 'Sure.' if question? message
  return 'Whatever.'
end

private
def question?(message)
  message.end_with? '?'
end

def yelling?(message)
  message.upcase!.nil? && message =~ /[A-Z]/
end

def nothing?(message)
  message.strip.empty?
end

end
