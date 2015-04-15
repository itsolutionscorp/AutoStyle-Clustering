class TeenTalk
  def initialize(phrase)
    @phrase = phrase
  end

  def shout?
    @phrase.upcase == @phrase && @phrase.downcase != @phrase
  end    

  def say_nothing?
    @phrase.strip.empty?
  end

  def ask_question?
    @phrase.end_with?("?")
  end
end

class Bob
  def hey(talk_to_bob)
    to_bob = TeenTalk.new(talk_to_bob)
    case
    when to_bob.shout?        then 'Woah, chill out!'
    when to_bob.say_nothing?  then 'Fine. Be that way!'
    when to_bob.ask_question? then 'Sure.' 
    else 'Whatever.'
    end
  end
end

