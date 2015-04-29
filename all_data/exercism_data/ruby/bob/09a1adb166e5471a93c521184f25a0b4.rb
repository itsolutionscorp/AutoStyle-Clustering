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

@teenager = ::Bob.new
p @teenager.hey('4?')

# p @teenager.hey('hey you there')
# p @teenager.hey('HEY YOU THERE?')
# p @teenager.hey('hey?')
# p @teenager.hey('')


# && phrase.match(/.*(?<!\?)$/)
