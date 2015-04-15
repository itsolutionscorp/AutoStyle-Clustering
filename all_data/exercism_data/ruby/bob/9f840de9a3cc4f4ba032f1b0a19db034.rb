class Bob
 def hey(message)
   case message
   when /^[ \t]+$/
     'Fine. Be that way!'
   when /(\n|^[0-9, ]+$)/
     'Whatever.'
   when ''
     'Fine. Be that way!'
   when /^[0-9, ]+\?$/
     'Sure.'
   when /^[^A-Z]+\?$/
     'Sure.'
   when /^([A-Z]|[0-9! ,%*@#$(^])+(\?)?$/
     'Woah, chill out!'
   when /\?$/
     'Sure.'
   else
     'Whatever.'
   end
 end
end
