class Bob

   def hey (sentence)
      case
      when say_nothing?(sentence)
         'Fine. Be that way.'
      when yelling?(sentence)
         'Woah, chill out!'
      when asking?(sentence)
         'Sure.'
      when statement?(sentence)
         'Whatever.'
      end
   end

   def say_nothing?(string)
      string.nil? || string.empty?
   end

   def yelling?(string)
      string == string.upcase
   end

   def asking?(string)
      string.end_with? '?'
   end

   def statement?(string)
      true
   end

end
