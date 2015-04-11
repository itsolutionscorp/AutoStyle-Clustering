class Bob

  def hey(message)
    return respond_to :blank if blank? message
    return respond_to :yell if yell? message
    return respond_to :question if question? message
    return respond_to :declarative
  end

  def respond_to(type)
    responses = {
      :question => "Sure.",
      :declarative => "Whatever.",
      :yell => "Woah, chill out!",
      :blank => "Fine. Be that way!"
    }
    responses[type]
  end

  private

  def blank?(message)
    message.nil? || message.gsub(/\s+/, "")[0].nil?
  end

  def yell?(message)
    message.match /^[A-Z\s\d[:punct:]]+$/
  end

  def question?(message)
    message.match /^.*\?$/
  end

end
