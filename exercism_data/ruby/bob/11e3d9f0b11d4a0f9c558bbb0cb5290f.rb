# class Bob
# 	def hey(sentence)
# 		if sentence.nil? || sentence.empty?
# 			'Fine. Be that way.'
# 		elsif sentence == sentence.upcase 
# 			'Woah, chill out!'
# 		elsif sentence.end_with?('?')
# 			'Sure.'
# 		else 
# 			'Whatever.'
# 		end
# 	end
# end

class Bob

	def hey(sentence)
		if speechless?(sentence)
			'Fine. Be that way.'
		elsif getting_yelled_at?(sentence)
			'Woah, chill out!'
		elsif question_asked?(sentence)
			'Sure.'
		else 
			'Whatever.'
		end
	end

	private 

	def speechless?(sentence)
  	sentence.nil? || sentence.empty?
  end

  def getting_yelled_at?(sentence)
 	  sentence == sentence.upcase
 	end

 	def question_asked?(sentence)
 		sentence.end_with?('?')
 	end
end
