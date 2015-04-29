class Bob
  def hey(say)
    return 'Fine. Be that way!' if say.strip == ''
    return 'Woah, chill out!' if say == say.upcase
    return 'Sure.' if say[-1] == '?'

    'Whatever.'
  end

end
