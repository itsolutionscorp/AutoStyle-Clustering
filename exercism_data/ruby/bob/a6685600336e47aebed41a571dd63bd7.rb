# overwriting class String
class String
  def silent?
    strip == ''
  end

  def questioning?
    match(/.*\?\z/)
  end

  def yelling?
    (match(/.*\!\z/) && (!match(/Let's/))) \
    || \
    (match(/[A-Z]+/) && upcase == self)
  end
end

# class bob
class Bob
  def hey(voice)
    return 'Woah, chill out!' if voice.yelling?
    return 'Sure.' if voice.questioning?
    return 'Fine. Be that way!' if voice.silent?
    'Whatever.'
  end
end
