class Bob
  def hey(string)
    sentence = Sentence.new(string)
    return 'Woah, chill out!' if sentence.shout?
    return 'Fine. Be that way!' if sentence.silence?
    return 'Sure.' if sentence.question?
    'Whatever.'
  end
end

class Sentence < Struct.new(:string)
  def shout?
    return false if silence?
    @string.upcase == @string
  end

  def question?
    @string[-1] == '?'
  end

  def silence?
    @string.strip.empty?
  end
end
