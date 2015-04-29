class	Bob 
	def hey string
    return 'Fine. Be that way!' if string.strip.empty?
    return 'Woah, chill out!' if string.upcase.eql?(string) && !string.scan(/[A-Z]/).empty?
    return 'Sure.' if string.end_with? ??
    'Whatever.'
  end
end
