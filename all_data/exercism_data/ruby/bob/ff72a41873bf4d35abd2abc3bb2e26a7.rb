class Bob
	def hey(msg)
		return 'Fine. Be that way!' if silence? msg
		return 'Woah, chill out!' if shouting? msg
		return 'Sure.' if asking? msg
		'Whatever.'
  end

  private

  def silence?(msg)
      msg.to_s.empty?
  end

  def shouting?(msg)
      msg == msg.upcase
  end

  def asking?(msg)
      msg.end_with? '?'
  end
end
