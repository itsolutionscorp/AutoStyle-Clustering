class Bob
	def hey(message)
		case message
		when message_is_blank? then 'Fine. Be that way!'
		when message_is_shouting? then 'Woah, chill out!'
		when message_is_a_question? then 'Sure.'
		else 'Whatever.'
		end
	end

	private

	def message_is_blank?
		lambda { |msg| msg.nil? || msg.strip.empty? }
	end

	def message_is_shouting?
		lambda { |msg| msg == msg.upcase }
	end

	def message_is_a_question?
		lambda { |msg| msg.end_with? '?' }
	end
end
