class Bob
  def hey(words)
    sentance = Sentance.new(words)

    if sentance.silence?
      'Fine. Be that way!' 
    elsif sentance.shouting?
      'Woah, chill out!' 
    elsif sentance.question?
      'Sure.'
    else 
      'Whatever.'
    end
  end
end

class Sentance
  attr_accessor :words

  def initialize(words)
    @words = words
  end
  
  def silence?
    words.strip.empty?
  end

  def shouting?
    words == words.upcase && words =~ /[A-Z]/
  end

  def question?
    words =~ /\?\z/
  end
end
