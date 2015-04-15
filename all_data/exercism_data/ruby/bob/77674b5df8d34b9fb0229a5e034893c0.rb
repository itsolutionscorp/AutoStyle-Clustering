class Bob
  def hey(message)
    response = 'Whatever.'
    response = 'Fine. Be that way!' if is_silent?(message)
    response = 'Sure.' if is_a_question?(message)
    response = 'Woah, chill out!' if is_yelled?(message)
    response
  end

private

def is_silent?(message)
  message.empty? || message.match(/\A\s+\Z/)
end

def is_a_question?(message)
  message.reverse.slice(0) == "?"
end

def is_yelled?(message)
  message.gsub(/[\d\s,?]/,'').size > 0 && message.upcase == message
end

end
