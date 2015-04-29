# Generic Person class, outlines basic behavior of a person
class Person
   @@Responses = { }
   # (public) hey - determines appropriate response to message
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
   # (private) shout? - tests whether a message is a shout
   def shout? (msg)
      msg.upcase == msg
   end
   # (private) silence? - tests if a message is silence
   def silence? (msg)
      msg.nil? || msg.empty?
   end
   # (private) question? - tests whether message is a question
   def question? (msg)
      msg.end_with? "?"
   end
end
# Bob is a person, he has his own personal responses
class Bob < Person
   # Bob's specific responses
   @@Responses = {
      :question  => 'Sure.',
      :statement => 'Whatever.',
      :shout     => 'Woah, chill out!',
      :silence   => 'Fine. Be that way!'
   }
end
