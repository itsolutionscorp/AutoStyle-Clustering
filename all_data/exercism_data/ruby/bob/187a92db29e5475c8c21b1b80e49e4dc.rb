class Bob
  def hey(msg)
    result = analyze(msg)
    case
    when result.question? then "Sure."
    when result.yelling? then "Woah, chill out!"
    when result.silence? then "Fine. Be that way."
    else
      "Whatever."
    end
  end

  private

  def analyze(msg)
    MessageAnalyzer.new(msg)
  end

end

class MessageAnalyzer
  attr_reader :msg

  def initialize(msg)
    @msg = msg || ''
  end

  def silence?
    msg.length == 0
  end

  def yelling?
    msg.length > 0 && msg == msg.upcase
  end

  def question?
    msg =~ /.*\?$/
  end
end
