# BOB.rb
class Bob
  def hey(tell)
    answer = 'Fine. Be that way!'
    answer = 'Whatever.' if tell_has_something?(tell)
    answer = 'Sure.' if question?(tell)
    answer = 'Woah, chill out!' if shouting?(tell)
    answer
  end
  
  def tell_has_something?(tell)
    tell.gsub(/\s/, '').length > 0
  end
  
  def shouting?(tell)
    has_letters(tell) && tell == tell.upcase
  end
  
  def question?(tell)
    tell[-1] == '?'
  end
  
  def has_letters(tell)
    tell.match(/[a-zA-Z]/)
  end
  
end
