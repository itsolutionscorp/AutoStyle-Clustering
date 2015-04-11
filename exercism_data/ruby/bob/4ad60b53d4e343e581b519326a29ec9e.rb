#!/usr/bin/ruby
# Bob exercise

class Bob
  def hey(what)
    what.strip!
    return 'Woah, chill out!' if what==what.upcase && what!=what.downcase
    return 'Sure.' if what[-1]=='?'
    return 'Fine. Be that way!' if what.empty?
    'Whatever.'
  end
end
