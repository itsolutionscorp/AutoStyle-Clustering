class Bob

  def hey(message)
    message = Message.new(message)
    {
      blank: "Fine. Be that way!",
      angry: 'Woah, chill out!',
      question: 'Sure.'
    }[message.type] || "Whatever."
  end
end

class Message

  def initialize( message )
    @content = message.to_s
  end

  def type
    value = :question if question?
    value = :angry    if angry?
    value = :blank    if blank?
    value
  end

private

  def blank?
    @content.lstrip.empty?
  end

  def angry?
    @content.upcase == @content
  end

  def question?
    @content.end_with? "?"
  end
end
