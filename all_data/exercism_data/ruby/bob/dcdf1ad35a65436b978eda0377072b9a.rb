class Bob 

  #BOB = 'Bob'

  def hey(phrase)
    return 'Fine. Be that way!' if phrase.strip.empty?
    return 'Woah, chill out!' if phrase == phrase.upcase
    return 'Sure.' if phrase[-1,1] == '?'
    
    'Whatever.'
  end


end

# bob = Bob.new
# puts bob.hey("hello?")
