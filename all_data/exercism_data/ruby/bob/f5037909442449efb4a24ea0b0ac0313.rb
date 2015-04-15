class Sentence
  def initialize(statement)
    @statement = statement
  end

	def yelling?
		@statement.match(/[a-zA-Z]/) && @statement == @statement.upcase 
	end

	def asking? 
		@statement[-1] == "?"
	end

	def silent?
		@statement.strip.empty?
	end
end


class Bob 
	def hey(statement)
		sentence = Sentence.new(statement)
		if sentence.yelling?
			"Woah, chill out!"
		elsif sentence.asking?
			"Sure."
		elsif sentence.silent?
			"Fine. Be that way!"
		else
			"Whatever."
		end
	end
end
