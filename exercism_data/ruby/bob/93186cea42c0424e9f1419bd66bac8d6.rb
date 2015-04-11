module StringHelper
	def silence?
		lambda {|str| str.to_s.strip == ''}
	end

	def shout? 
		lambda {|str| str.to_s.upcase == str}
	end

	def question? 
		lambda {|str| str.to_s.end_with?('?')}
	end
end

class Bob
	include StringHelper
	def hey str
		bob_answers = {
		    silence? => 'Fine. Be that way!',
			shout? => 'Woah, chill out!',
			question? => 'Sure.'
		}
		
		bob_answers.each {|my_lambda, response| 
			return response if my_lambda.call(str)
		}
		'Whatever.'
	end
end
