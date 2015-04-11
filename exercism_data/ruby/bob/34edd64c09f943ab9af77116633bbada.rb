class Bob

	def hey( msg )
		
		if  msg.strip.empty?
			return  'Fine. Be that way!' 
		
		elsif msg.upcase == msg && msg[/[A-Z]/]
			return 'Woah, chill out!' 

		elsif msg[-1] == '?'
			return 'Sure.' 
		
		else
			'Whatever.'
    end
	end
end
