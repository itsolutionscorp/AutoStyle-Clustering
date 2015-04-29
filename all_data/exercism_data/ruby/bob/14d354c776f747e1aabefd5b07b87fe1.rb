class Bob

	def hey (message)

		all_cases = {:silence? => 'Fine. Be that way!', :yelling? => 'Woah, chill out!', :asking? => 'Sure.'}

		response = all_cases.keys.find do |meth_name|
			send(meth_name, message)
		end

		all_cases.fetch(response, 'Whatever.')

	end


	def yelling? (message)
		message.upcase == message
	end

	def asking? (message)
		message[-1].chr == '?'
	end

	def silence? (message)
		message.nil? || message.strip.empty?
	end

end
