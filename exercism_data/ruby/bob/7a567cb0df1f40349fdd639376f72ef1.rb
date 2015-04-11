class Bob

	def hey( msg )
		# He says 'Fine. Be that way!' if you address him without actually saying anything.
		return  'Fine. Be that way!' if ( msg.strip.empty? )

		# He answers 'Woah, chill out!' if you yell at him (ALL CAPS).
		return 'Woah, chill out!' if (msg.upcase == msg && msg[/[A-Z]/])

		# Bob answers 'Sure.' if you ask him a question.
		return 'Sure.' if (msg[-1] == '?')

		# He answers 'Whatever.' to anything else.
		'Whatever.'
	end
end
