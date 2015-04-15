module StringHelper
  refine String do
    def space?
      self.strip.empty?
    end

    def yell?
      self.upcase == self
    end

    def question?
      self.end_with? '?'
    end
  end
end

using StringHelper

class Bob
  def hey(message)
    if message.space?
      'Fine. Be that way!' 
    elsif message.yell?
      'Woah, chill out!' 
    elsif message.question?
      'Sure.' 
    else
      'Whatever.'
    end
  end
end
