class Bob
   def hey (msg)
      if silence? msg
         'Fine. Be that way!'
      elsif shout? msg
         'Woah, chill out!'
      elsif question? msg
         'Sure.'
      else
         'Whatever.'
      end
   end

   private

   def shout? (msg)
      msg.upcase == msg
   end

   def silence? (msg)
      msg.nil? || msg.empty?
   end

   def question? (msg)
      msg.end_with? "?"
   end
end
