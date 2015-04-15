class Bob
	RESPONDERS = [
		{ reply: 'Fine. Be that way!', 	matcher: ->(i) { i.nil? || i.empty? } },
		{ reply:  'Woah, chill out!', 	matcher: ->(i) { i.upcase == i } },
		{ reply: 'Whatever.', 					matcher: ->(i) { i.end_with?('.','!') } },
		{ reply: 'Sure.', 							matcher: ->(i) { i.end_with?('?') } }
	]

	def hey(input)
		RESPONDERS.find { |m| m[:matcher].call(input) }[:reply] rescue nil
	end
end
