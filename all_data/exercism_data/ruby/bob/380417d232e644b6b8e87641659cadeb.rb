class Bob

  def hey(message)
    message = Message.new(message)
    {
      blank: "Fine. Be that way!",
      angry: 'Woah, chill out!',
      question: 'Sure.',
      whatever: 'Whatever.'
    }[message.type]
  end
end

class Message
  attr_reader :type

  def initialize(message)
    @content = message.to_s
    @type = blank || angry || question || :whatever
  end

private

  def strip_string_and_check_if_empty
    @content.lstrip.empty?
  end

  def blank
    :blank if strip_string_and_check_if_empty
  end

  def angry
    :angry if @content.upcase == @content && !strip_string_and_check_if_empty
  end

  def question
    :question if @content.end_with? "?"
  end
end
