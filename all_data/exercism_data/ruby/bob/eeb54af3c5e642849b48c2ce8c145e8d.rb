class Person
   @@Responses = { }

   def hey (msg)
      if silence? msg
         @@Responses[:silence]
      elsif shout? msg
         @@Responses[:shout]
      elsif question? msg
         @@Responses[:question]
      else
         @@Responses[:statement]
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

class Bob < Person
   @@Responses = {
      :question  => 'Sure.',
      :statement => 'Whatever.',
      :shout     => 'Woah, chill out!',
      :silence   => 'Fine. Be that way!'
   }
end
