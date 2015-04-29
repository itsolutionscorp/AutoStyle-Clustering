class Bob

  def new
  end

	def hey(msg)
    if msg.nil? or msg.length == 0
      return 'Fine. Be that way.'
    elsif msg == msg.upcase
      return 'Woah, chill out!'
    elsif msg[-1] == '.' or msg[-1] == '!'
      return 'Whatever.'
    elsif msg[-1] == '?'
      return 'Sure.'
    end
  end
end
