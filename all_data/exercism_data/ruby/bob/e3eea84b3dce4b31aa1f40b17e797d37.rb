class Bob
  def initialize
  end
  
  def hey(message)
    #respond to silence
    return 'Fine. Be that way!' if message.strip == ''
    #respond to yelling
    return 'Woah, chill out!' if message == message.upcase
    #respond to questions
    return 'Sure.' if message[-1] == '?' 
    'Whatever.'
  end 
end
