class Bob

  def hey(z)
    response = 'Whatever.'
    response = 'Woah, chill out!' if z && z == z.upcase
    response = 'Sure.' if z && z[-1] == '?'
    response = 'Fine. Be that way.' unless z && z.strip.length > 0
    response
  end
end
