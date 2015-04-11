class Bob
	@response

   def initialize
   end

   # class method
   def hey (statement)
   	if statement.upcase == statement && statement.downcase != statement
     	@response = 'Woah, chill out!'
     elsif statement.strip.empty?
     	@response = 'Fine. Be that way!'
     elsif statement[-1,1] == '?'
     	@response = 'Sure.'
     else
     	@response = 'Whatever.'
     end
     @response
   end
 end
