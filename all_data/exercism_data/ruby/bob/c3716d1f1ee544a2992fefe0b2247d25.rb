class Bob
	def hey(phrase)
		if silent(phrase)
			'Fine. Be that way!'
		elsif yelling(phrase)
			'Woah, chill out!'
		elsif 	question(phrase)		
			'Sure.'
		else 
			"Whatever."
		end
	end


    def silent(phrase)
      phrase.match(/\A\s*\z/)
    end

    def yelling(phrase)
      phrase.match(/^(?!.*[a-z]).+$/) && phrase.match(/[A-Z]/)  	
    end

    def question(phrase)
    	phrase.end_with?('?')
    end
end
