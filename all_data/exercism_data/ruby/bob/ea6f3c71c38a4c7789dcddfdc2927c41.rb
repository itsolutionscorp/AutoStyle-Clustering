class Voice
  def initialize(string)
    @s = string
  end

  def silent?
    @s.strip == ''
  end

  def questioning?
    @s.match(/.*\?\z/)
  end

  def yelling?
    (@s.match(/.*\!\z/) && (!@s.match(/Let's/))) \
    || \
    (@s.match(/[A-Z]+/) && @s.upcase == @s)
  end

  def to_s
    @s
  end
end

# class bob
class Bob
  def hey(voice)
    voice = Voice.new(voice)
    return 'Woah, chill out!' if voice.yelling?
    return 'Sure.' if voice.questioning?
    return 'Fine. Be that way!' if voice.silent?
    'Whatever.'
  end
end
