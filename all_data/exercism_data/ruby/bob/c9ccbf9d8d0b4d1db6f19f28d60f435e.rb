class TeenTalk
  def initialize(words)
    @words = words
  end
  #add to String class  
  def shout?
    @words.upcase == @words && @words.downcase != @words
  end    

  def say_nothing?
    @words.strip.empty?
  end

  def ask_question?
    @words.end_with?("?")
  end
end

class Bob

  def hey(words_to_bob)
    to_bob = TeenTalk.new(words_to_bob)
    case
      when to_bob.shout?        then 'Woah, chill out!'
      when to_bob.say_nothing?  then 'Fine. Be that way!'
      when to_bob.ask_question? then 'Sure.' 
      else 'Whatever.'
    end
  end

end

