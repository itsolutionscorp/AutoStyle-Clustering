class Bob

  def hey(msg)

    return 'Fine. Be that way!' if no_response?(msg)
    return 'Woah, chill out!' if yelling?(msg)
    return 'Sure.' if question?(msg)
    'Whatever.'
  end

  private

  def no_response?(msg)
    msg.scan(/\w+/).empty?
  end

  def yelling?(msg)
    msg == msg.upcase
  end

  def question?(msg)
    msg.end_with?("?")
  end
end
