class Bob

   def hey(input)
       case
         when input.size == 0 || !input.match(/[^\s]+/)
             return 'Fine. Be that way!'
         when input.match(/[A-Z]+/) && !input.match(/[a-z]+/)
             return 'Woah, chill out!'
         when input.end_with?('?')
             return 'Sure.'
         else
             return 'Whatever.'
         end
    end

end
