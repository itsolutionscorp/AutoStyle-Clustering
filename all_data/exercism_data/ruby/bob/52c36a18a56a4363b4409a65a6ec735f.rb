class Bob
   def hey(str)
   	 if str.strip.empty?
   	 	'Fine. Be that way!'
   	 elsif str == str.upcase and str.match(/[a-zA-Z]/) != nil
   	 	'Woah, chill out!'
     elsif str[-1,1] == '?'
         'Sure.'
     else
     	 'Whatever.'
     end
   end
end