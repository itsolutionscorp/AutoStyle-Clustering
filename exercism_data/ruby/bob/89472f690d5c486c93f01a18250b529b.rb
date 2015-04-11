class Bob
   def initialize()
   end

   def hey(str)
   	 if str.gsub(' ', '') == ''
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
