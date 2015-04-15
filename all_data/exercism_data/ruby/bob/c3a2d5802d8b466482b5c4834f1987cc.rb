class Bob
	def hey(statement)
		statement = Statement.new(statement)

		case 
		when statement.shouted?
			"Woah, chill out!"
		when statement.question?
			"Sure."
		when statement.silent?
			"Fine. Be that way!"
		else
			"Whatever."
		end
	end
end

class Statement < String 

	def initialize(string)
		# catches the case with only non-letter chars, but keeps '?'
		unless string =~ /[a-zA-Z]/ 
			string = "nothing string" + string 
		end

		super(string)
	end 

	# the statement is all caps
	def shouted?
		self == self.upcase
	end

	# the statement is a question
	def question?
		self.end_with?('?')
	end

	# the statement is only white space
	def silent?
		self =~ /nothing string\s*\z/
	end
end
