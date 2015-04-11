class Bob
  def hey(message)
    response = nil
    teen_message = message
    if message.nil? or message.empty?
      response = 'Fine. Be that way!'
    elsif teen_message.upcase!.nil?  
      response = 'Woah, chill out!'
    elsif message.end_with?(".") or message.end_with?("!")
      response = 'Whatever.'
    elsif message.include?("?")
      response = 'Sure.'
    end
    return response
  end
end
