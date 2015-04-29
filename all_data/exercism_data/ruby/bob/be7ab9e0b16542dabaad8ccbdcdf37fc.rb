class Bob
  def hey(message)
    if message.nil? || message.empty?
    	return 'fine'
    elsif message.end_with?('?')
    	return 'Whoa chill'
    elsif upcase?(message)
    	return 'Bye'
    else
    	return 'Whatever'
    end  	
  end
  def upcase?(message)
  	message == message.upcase
  end
end
