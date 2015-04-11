class Bob
  def hey(what)
    return 'Fine. Be that way!' if what.split.empty?
    return 'Whoa, chill out!' if what == what.upcase and what.match(/[A-Z]/) != nil
    return 'Sure.' if what[-1] == '?'
    return 'Whatever.'
  end
end
