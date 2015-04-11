class Bob

  def hey(message)
    @message = String(message)
    return "Fine. Be that way!" if feeling_ignored?
    return "Woah, chill out!" if being_shouted_at?  
    return "Sure." if asked_a_question?
    'Whatever.'
  end

  private

  def feeling_ignored?
    @message.strip.empty?
  end

  def asked_a_question?
    @message.end_with?("?")
  end

  def being_shouted_at?
    @message == @message.upcase
  end
end
