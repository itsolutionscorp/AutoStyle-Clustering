class Bob
  def hey(message)
    message.strip!

    type = :whatever
    type = :question if /\?\z/.match message
    type = :yelling if message == message.upcase && /[a-zA-Z]/.match(message)
    type = :blank if message.empty?

    respond type
  end

private

  def respond(type)
    case type
      when :question then 'Sure.'
      when :yelling then 'Woah, chill out!'
      when :blank then 'Fine. Be that way!'
      else 'Whatever.'
    end
  end
end
