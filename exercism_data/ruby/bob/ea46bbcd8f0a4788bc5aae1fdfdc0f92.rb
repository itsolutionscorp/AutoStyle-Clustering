# encoding: utf-8

class Bob
  def hey(message)

    if message.strip.empty?
      'Fine. Be that way!'
    elsif message.downcase != message && message.upcase == message
      'Woah, chill out!'
    elsif message.end_with?('?')
      'Sure.'
    else
      'Whatever.'
    end

  end
end
