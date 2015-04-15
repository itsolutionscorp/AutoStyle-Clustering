class Bob
	def hey sentence
		sentence_type = SentenceType.new sentence
		return 'Fine. Be that way!' if sentence_type.empty?
		return 'Woah, chill out!' if sentence_type.yelling?
		return 'Sure.' if sentence_type.question?
		return 'Whatever.'
	end	
private 
	class SentenceType
		attr_reader :sentence

		def initialize sentence
			@sentence = sentence
		end	

		def yelling?
		 	sentence.upcase == sentence		
		end
		
		def question?
			sentence[-1] == '?'
		end 

		def empty?
			sentence.nil? || sentence.strip.empty?
		end	
	end
end	
