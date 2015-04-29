class Bob
 def hey(words_i_said)
   if words_i_said.downcase !=words_i_said.upcase && words_i_said == words_i_said.upcase
     'Woah, chill out!'
   elsif words_i_said.byteslice(-1) == "?"
     'Sure.'
   else
     'Whatever.'
   end
 end
end
