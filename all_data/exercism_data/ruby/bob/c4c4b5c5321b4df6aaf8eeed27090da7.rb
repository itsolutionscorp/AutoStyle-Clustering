class Bob

   def hey (s)

      # Say nothing
      if !s || s == ''
         return 'Fine. Be that way.'

      # Shouting
      elsif s == s.upcase
         return 'Woah, chill out!'

      # Question
      elsif s[-1] == '?'
         return 'Sure.'

      # Statement
      else
         return 'Whatever.'
      end
   end
end
