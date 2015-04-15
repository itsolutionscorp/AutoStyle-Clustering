class Bob
  
  def initialize
  end
  
  def hey(message)
    if message == message.upcase && message != message.downcase
      'Woah, chill out!'
    elsif message.end_with?("?")
      'Sure.'
    elsif message.strip == ''
      "Fine. Be that way!"
    else
    'Whatever.'
    end
  end
  
end
