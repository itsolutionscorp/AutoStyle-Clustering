class SilenceMatcher
	def self.matches?(input)
		input.empty?
	end

	def self.message
		'Fine. Be that way!'
	end
end

class ShoutingMatcher
	def self.matches?(input)
		input.downcase.upcase == input
	end

	def self.message
		'Woah, chill out!'
	end
end

class StatementMatcher
	def self.matches?(input)
		%w(. !).include?(input[-1]) && !ShoutingMatcher.matches?(input)
	end

	def self.message
		'Whatever.'
	end
end

class QuestionMatcher
	def self.matches?(input)
		input[-1] == '?' && !ShoutingMatcher.matches?(input)
	end

	def self.message
		'Sure.'
	end
end

class Bob
	MATCHERS = [SilenceMatcher, StatementMatcher, QuestionMatcher, ShoutingMatcher]

	def hey(input)
		return SilenceMatcher.message unless input.is_a? String
		MATCHERS.each { |m| return m.message if m.matches?(input) }
	end
end
