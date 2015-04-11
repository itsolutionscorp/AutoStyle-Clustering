class Matcher
	def self.responds(message, options)
		define_singleton_method(:message) { message }

		define_singleton_method(:matches?) { |input| options[:to].call(input) }
	end
end

class SilenceMatcher < Matcher
	responds 'Fine. Be that way!', to: ->(i) { i.empty? }
end

class ShoutingMatcher < Matcher
	responds 'Woah, chill out!', to: ->(i) { i.upcase == i }
end

class StatementMatcher < Matcher
	responds 'Whatever.', to: ->(i) { i.end_with?('.','!') }
end

class QuestionMatcher < Matcher
	responds 'Sure.', to: ->(i) { i.end_with?('?') }
end

class Bob
	MATCHERS = [SilenceMatcher, ShoutingMatcher, StatementMatcher, QuestionMatcher]

	def hey(input)
		MATCHERS.find { |m| m.matches?(input.to_s) }.message
	end
end
