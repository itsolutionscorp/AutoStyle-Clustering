class Matcher
	class << self
		def shouting?(input)
			ShoutingMatcher.matches?(input)
		end
	end
end

class SilenceMatcher < Matcher
	class << self
		def matches?(input)
			empty?(input) || nil?(input)
		end

		def message
			'Fine. Be that way!'
		end

		private

		def empty?(input)
			input == ''
		end

		def nil?(input)
			input == nil
		end
	end
end

class ShoutingMatcher < Matcher
	class << self
		def matches?(input)
			shouting?(input)
		end

		def message
			'Woah, chill out!'
		end

		private

		def shouting?(input)
			input =~ /^[^a-z]+$/
		end
	end
end

class StatementMatcher < Matcher
	class << self
		def matches?(input)
			(telling?(input) || pushing?(input)) && !shouting?(input)
		end

		def message
			'Whatever.'
		end

		private

		def telling?(input)
			input =~ /.*\.\Z/
		end

		def pushing?(input)
			input =~ /.*!\Z/
		end
	end
end

class QuestionMatcher < Matcher
	class << self
		def matches?(input)
			asking?(input) && !shouting?(input)
		end

		def message
			'Sure.'
		end

		private

		def asking?(input)
			input =~ /.*\?\Z/
		end
	end
end

class Bob
	MATCHERS = [SilenceMatcher, StatementMatcher, ShoutingMatcher, QuestionMatcher]
	def hey(input)
		MATCHERS.each { |m| return m.message if m.matches?(input) }
	end
end
