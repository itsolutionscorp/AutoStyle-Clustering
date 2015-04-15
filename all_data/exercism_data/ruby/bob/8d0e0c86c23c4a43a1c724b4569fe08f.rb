# BOB.rb
class Bob
  def hey(tell)
    case
    when tell_has_nothing?(tell)
      'Fine. Be that way!'
    when shouting?(tell)
      'Woah, chill out!'  
    when question?(tell)
      'Sure.'
    else
      'Whatever.'
    end
  end
  
  def tell_has_nothing?(tell)
    tell.strip.empty?
  end
  
  def shouting?(tell)
    has_letters?(tell) && tell.eql?(tell.upcase)
  end
  
  def question?(tell)
    tell.end_with?('?')
  end
  
  def has_letters?(tell)
    tell.match /[[:alpha:]]/
  end
  
end
