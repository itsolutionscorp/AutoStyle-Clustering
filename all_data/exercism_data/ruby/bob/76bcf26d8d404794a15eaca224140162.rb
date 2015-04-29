class Bob
	def hey(str)

		str.strip!

		if str == str.upcase && /[A-Z]/ =~ str 
			'Woah, chill out!'
		elsif str.end_with?('?')
			'Sure.'
		elsif str.empty? 
			'Fine. Be that way!'	
		else
			'Whatever.'
		end

		
	end	
end
