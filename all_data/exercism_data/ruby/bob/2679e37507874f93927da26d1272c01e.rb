class Bob
	def hey statement
    interpreter = Interpreter.new(statement)
		return 'Woah, chill out!' if interpreter.shouting?
		return 'Sure.' if interpreter.question?
		return 'Fine. Be that way!' if interpreter.silence?
		"Whatever."
	end
end

class Interpreter
  def initialize statement
    @statement = statement
  end

	def shouting?
		return false unless /[A-Z]/ =~ @statement
		@statement == @statement.upcase
	end

	def question?
    @statement.end_with? "?" 
	end

	def silence?
    @statement.strip.empty?
	end
end
