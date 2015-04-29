class Bob
	def hey(msg)

    # Not saying anything
    if msg.nil? or msg.length == 0
      return 'Fine. Be that way.'

    # Yelling
    elsif msg == msg.upcase
      return 'Woah, chill out!'

    # question
    elsif msg[-1] == '?'
      return 'Sure.'

    # say something
    else
      return 'Whatever.'
    end
  end
end
