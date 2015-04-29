class Bob
	def hey(words)
		greeting = Greeting.new(words)
		case
		when greeting.yelling?  then "Woah, chill out!"
		when greeting.question? then "Sure."
		when greeting.silence?  then "Fine. Be that way!"
		else
			"Whatever."
		end
	end
end

class Greeting
	def initialize(words)
		@words = words
	end

	def question?
		@words[@words.length-1] == "?"
	end

	def silence?
		@words.strip.empty?
	end

	def yelling?
		@words.match(/[A-Z]/) && @words.upcase == @words
	end
end
