class Bob
  def hey(words)
   if words.nil? or words.strip.length == 0
     'Fine. Be that way!'
   elsif words == words.upcase
     'Woah, chill out!'
   elsif words[-1] == '?'
     'Sure.'
   else
     'Whatever.'
   end
  end
end
