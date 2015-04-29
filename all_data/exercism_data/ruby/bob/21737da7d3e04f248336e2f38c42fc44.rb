class Bob
  attr_reader :cerebrum

  def initialize
    @cerebrum = Cerebrum.new
  end

  def hey(message)
    cerebrum.process(message)
  end
end

class Cerebrum
  def process(input)
    case detect_sentiment(input)
    when :silence
      'Fine. Be that way!'
    when :yelling
      'Woah, chill out!'
    when :questioning
      'Sure.'
    when :ambiguous
      'Whatever.'
    end
  end

  def detect_sentiment(input)
    if input.strip.empty?
      :silence
    elsif input.downcase != input and input.upcase == input
      :yelling
    elsif input.end_with?('?')
      :questioning
    else
      :ambiguous
    end
  end
end
