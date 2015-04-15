# Bob the Lackadaisical Teenager

class Bob
  def hey(message)
    if message.strip.empty?
      'Fine. Be that way!'
    elsif message == message.upcase && message =~ /[A-Z]/
      'Woah, chill out!'
    elsif message.end_with?('?')
      'Sure.'
    else
      'Whatever.'
    end
  end
end
