class Bob

  BOB = 'Bob'

  def hey(phrase)
    return 'Woah, chill out!' if phrase == phrase.upcase
    return 'Sure.' if phrase[-1,1] == '?'
    return 'Fine. Be that way!' if phrase == ''
    'Whatever.'
  end


end

# bob = Bob.new
# puts bob.hey("hello?")
