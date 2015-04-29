class Bob
  def hey t = nil
    return 'Fine. Be that way!' unless t
    return 'Sure.' if t[-1] == '?'
    return 'Woah, chill out!' if t.upcase == true
    'Whatever.'
  end

end
