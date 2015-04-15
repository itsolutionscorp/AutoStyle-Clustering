class Bob

  def hey(message)
    message = Message.new(message)
    case message.type
    when :blank
      "Fine. Be that way!"
    when :angry
      'Woah, chill out!'
    when :question
      'Sure.'
    else
      'Whatever.'
    end
  end
end

class Message

  def initialize( message )
    @content = message.to_s
    @type = type
  end

  def type
    value = :question if question?
    value = :angry if angry?
    value = :blank if blank?
    value
  end

private

  def blank?
    @content.nil? || @content.lstrip.empty?
  end

  def angry?
    @content.upcase == @content
  end

  def question?
    @content.end_with? "?"
  end
end
