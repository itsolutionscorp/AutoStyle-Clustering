class Bob
  def hey(message)
    if message == ('') || message == nil
    	return 'fine'
    elsif message.upcase == message
    	return 'Whoa chill'
    elsif message.end_with('?')
    	return 'Bye'
    end  	
  end
end
