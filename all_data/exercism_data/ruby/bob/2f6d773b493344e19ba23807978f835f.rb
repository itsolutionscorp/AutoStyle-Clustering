module StringHelper
	def silence?
		lambda {|str| str.to_s.strip.empty?}
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

		bob_answers.each {|what_you_say, response| 
			return response if what_you_say.call(str)
		}
		'Whatever.'
	end
end