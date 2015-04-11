class Bob
  def hey(message)
    sentence = Discussion.new(message)
    return 'Fine. Be that way!' if sentence.nothing_to_say?
    return 'Woah, chill out!' if sentence.yell?
    return 'Sure.' if sentence.question?
    return 'Whatever.'
  end

end

class Discussion

  def initialize(message)
    @message = message
  end

  def yell?
    @message == @message.upcase
  end

  def question?
    @message.end_with?('?')
  end

  def nothing_to_say?
    @message.strip.size.zero?
  end
end
