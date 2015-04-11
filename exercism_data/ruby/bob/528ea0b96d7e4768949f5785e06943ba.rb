class Bob

   def hey (sentence)
      case
      when match_say_nothing?(sentence)
         'Fine. Be that way.'
      when match_yelling?(sentence)
         'Woah, chill out!'
      when match_asking?(sentence)
         'Sure.'
      when match_statement?(sentence)
         'Whatever.'
      end
   end

   def match_say_nothing?(string)
      !string || string == ''
   end

   def match_yelling?(string)
      string == string.upcase
   end

   def match_asking?(string)
      string.end_with? '?'
   end

   def match_statement?(string)
      true
   end

end
