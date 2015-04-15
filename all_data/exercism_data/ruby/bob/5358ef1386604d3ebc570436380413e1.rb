#!/usr/bin/ruby
# Bob exercise

class Bob
  def hey(what)
    # Remove surrounding whitespace / nul at end
    what.strip!
    
    case
    when what==what.upcase && what!=what.downcase
      respond_shout
    when what[-1]=='?'
      respond_question
    when what.empty?
      respond_nothing
    else
      respond_default
    end
  end
  
  def respond_shout
    'Woah, chill out!'
  end
  
  def respond_question
    'Sure.'
  end
  
  def respond_nothing
    'Fine. Be that way!'
  end
  
  def respond_default
    'Whatever.'
  end
end
