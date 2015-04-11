class Bob
  def hey(message)
    if message.nil? || message.strip.length == 0
       'Fine. Be that way!'
    elsif message.upcase == message
       'Woah, chill out!'
    elsif message =~ /\?\z/
     'Sure.'
    else
      'Whatever.'
    end
  end
end
