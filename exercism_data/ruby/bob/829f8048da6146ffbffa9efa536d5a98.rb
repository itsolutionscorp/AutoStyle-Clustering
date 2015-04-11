class Bob
  def hey(something)
    return 'Fine. Be that way.' if something==''
    return 'Fine. Be that way.' if something.nil?
    return 'Sure.' if something[-1]=='?'
    return 'Woah, chill out!' if something.upcase == something
    'Whatever.'
  end
end
