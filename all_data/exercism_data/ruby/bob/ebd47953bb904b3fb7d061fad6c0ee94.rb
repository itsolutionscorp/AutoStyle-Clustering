# Bob, a teenager.
class Bob
	# Have Bob respond to a message.
	#
	# @param text [String] message spoken to Bob.
	# @return ['Fine. Be that way.'] if `text` is blank.
	# @return ['Woah, chill out!'] if `text` is shouted.
	# @return ['Sure.'] if `text` is a question.
	# @return ['Whatever'] otherwise
	# @see Phrase#question?
	# @see Phrase#shout?
	def hey(text)
		# use #to_s to gracefully handle `nil`.
		phrase = Phrase.new(text.to_s)

		if phrase.empty?
			'Fine. Be that way.'
		elsif phrase.shout?
			'Woah, chill out!'
		elsif phrase.question?
			'Sure.'
		else
			'Whatever.'
		end
	end
end

# @note This really should be in phrase.rb, but exercism only allows one file
#   submission.
#
# Wraps a String said to {Bob} so that the String can be checked for what type
# of sentence it contains.
class Phrase < String
	# Returns whether this phrase is a question.
	#
	# @return [true] if this phrase ends in a '?'.
	# @return [false] otherwise
	def question?
		end_with? '?'
	end

	# Returns whether this phrase is a shout.
	#
	# @return [true] if this phrase is in all uppercase.
	# @return [false] otherwise
	def shout?
		upcase == self
	end
end
