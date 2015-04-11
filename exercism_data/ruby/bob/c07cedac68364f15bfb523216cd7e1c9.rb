class Bob
  def hey(message)

    if message.to_s == ''
      response = 'Fine. Be that way!'
    elsif message == message.upcase
      response = 'Woah, chill out!'
    elsif message.end_with?("?")
      response = 'Sure.'
    else
      response = 'Whatever.'
    end

  end
end
