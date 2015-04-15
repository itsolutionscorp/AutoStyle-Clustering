class Bob
      def hey(ask)
            if ask == "" || ask == nil
            	'Fine. Be that way.'
            elsif ask.upcase == ask
            	'Woah, chill out!'
            elsif ask.end_with?("?") # elsif ask.include?("?")
            	'Sure.'
            else
      		'Whatever.'
            end
	end
	
end
