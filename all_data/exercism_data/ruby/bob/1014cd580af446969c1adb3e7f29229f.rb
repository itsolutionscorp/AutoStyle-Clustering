class Bob
	def initialize
		@answers = {
			"Woah, chill out!" =>
			{
				:regexps => [
					/[[:graph:]]([^a-z]{1,})$/
				]
			},
			"Sure." => {
				:regexps => [
					/.*\?$/
				]
			},
			"Fine. Be that way!" => {
				:regexps => [
					/([[:space:]]*)$/
				]
			}
		}
		@default_answer = "Whatever."
	end

	def hey(msg)
		@answers.each do |k, v|
			validator = RegExpValidator.new(v)
			if validator.validate(msg)
				return k
			end
		end
		return @default_answer
	end
end

class RegExpValidator
	def initialize(validator)
		@validator = validator[:regexps]
	end

	def validate(string)
		res = true
		@validator.each do |regex|
			res = res && ((regex =~ string) == 0)
		end
		res
	end
end
