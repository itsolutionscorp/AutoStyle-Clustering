class Bob

   def hey (s)
      case s
      when nil,''       # Say nothing
         'Fine. Be that way.'
      when s.upcase # Shouting
         'Woah, chill out!'
      when /\?$/     # Question
         'Sure.'
      else          # Statement
         'Whatever.'
      end
   end
end
