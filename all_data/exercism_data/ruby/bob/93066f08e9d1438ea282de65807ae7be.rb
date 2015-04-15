# Meet Bob, he is a man of few words, try saying something to him
class Bob
   # These are the defined responses
   @@question = 'Sure.'
   @@statement = 'Whatever.'
   @@shout = 'Woah, chill out!'
   @@silence = 'Fine. Be that way!'

   # (public) hey - determines appropriate response to message
   def hey (msg)
      if silence? msg
         @@silence
      elsif shout? msg
         @@shout
      elsif question? msg
         @@question
      else
         @@statement
      end
   end

   private
   # (private) shout? - tests whether a message is a shout
   def shout? (msg)
      msg.upcase == msg
   end
   # (private) silence? - tests if a message is silence
   def silence? (msg)
      msg.nil? or msg.empty?
   end
   # (private) question? - tests whether message is a question
   def question? (msg)
      msg.end_with? "?"
   end
end
