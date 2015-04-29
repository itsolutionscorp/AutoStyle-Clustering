class Bob
  def hey(input)
    prompt = SpeechString.new(input)
    
    case
    when prompt.empty?
      'Fine. Be that way.'
    when prompt.question?
      'Sure.'
    when prompt.yelling?
      'Woah, chill out!'
    else
      'Whatever.'
    end
  end
end

class SpeechString
  def initialize(str)
    @string = str
  end

  def empty?
    @string.nil? || @string.empty?
  end

  def question?
    @string.end_with?('?')
  end

  def yelling?
    @string == @string.upcase
  end

  def method_missing(meth)
    @string.send(meth)
  end
end
