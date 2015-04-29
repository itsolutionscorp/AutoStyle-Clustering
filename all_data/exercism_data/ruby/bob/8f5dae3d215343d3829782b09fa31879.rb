module Tager
  #add to String class  
  def shout?
    upcase == self && downcase != self
  end    

  def say_nothing?
    strip.empty?
  end

  def ask_question?
    end_with?("?")
  end
end

class String
  include Tager
end

class Bob
  include Tager

  def hey(to_bob)
    case
      when to_bob.shout?        then 'Woah, chill out!'
      when to_bob.say_nothing?  then 'Fine. Be that way!'
      when to_bob.ask_question? then 'Sure.' 
      else 'Whatever.'
    end
  end

end

