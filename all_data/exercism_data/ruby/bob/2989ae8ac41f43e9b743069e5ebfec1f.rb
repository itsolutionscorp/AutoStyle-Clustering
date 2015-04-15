class Bob
	def hey sth
		return 'Fine. Be that way!' if sth.strip.empty?
		return 'Whatever.' if sth =~ /^[, 0-9]*$/
		return 'Whatever.' if sth =~ /^.*\.+$/
		return 'Sure.' if sth =~ /^[0-9,\ ]*\?+$/
		return 'Whoa, chill out!' if sth =~ /^[0-9, A-Z]*\?+$/
		return 'Whoa, chill out!' if sth =~ /^[^a-z]*!+$/
		return 'Sure.' if sth =~ /\?/
		return 'Whoa, chill out!' if sth =~ /\A[0-9, A-Z]*!*\z/
		'Whatever.'
	end
end
