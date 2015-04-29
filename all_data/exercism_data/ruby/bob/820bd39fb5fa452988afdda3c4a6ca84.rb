class Bob
  def hey txt
   return 'Fine. Be that way!' if txt==''
   return 'Fine. Be that way!' if txt==' '*txt.size
   if (txt.downcase==txt.swapcase and /[A-Z]/.match(txt))
	return 'Woah, chill out!'
   elsif txt[-1]=='?'
	   return 'Sure.'

   end
   'Whatever.'
  end
end
