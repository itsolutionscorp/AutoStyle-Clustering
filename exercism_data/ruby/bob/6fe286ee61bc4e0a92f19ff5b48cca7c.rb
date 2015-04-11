# Warning: I've never programmed Ruby, so this probably sucks!
#
# Also, this was very helpful: http://ruby-doc.org/core-2.0.0/String.html

class Bob
  def hey(x)
    return 'Woah, chill out!' if x.upcase == x and x.downcase != x
    return 'Sure.' if x.end_with? '?'
    return 'Fine. Be that way!' if x.strip.empty?
    return 'Whatever.'
  end
end
