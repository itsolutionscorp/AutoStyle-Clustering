class Bob

  def hey(message)
    message = Message.new(message)
    case message.type
    when :blank then "Fine. Be that way!"
    when :angry then 'Woah, chill out!'
    when :question then 'Sure.'
    when :whatever then 'Whatever.'
    end
  end
end

class Message

  def initialize(message)
    @content = message.to_s
  end

  def type
    blank || angry || question || :whatever
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
