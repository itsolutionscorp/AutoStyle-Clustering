# encoding: utf-8

class Bob
  def hey(message)

    if message.upcase == message
      'Woah, chill out!'
    elsif message.end_with?('?')
      'Sure.'
    else
      'Whatever.'
    end

  end
end
