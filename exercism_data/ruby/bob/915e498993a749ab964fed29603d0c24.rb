class Bob
  def hey(msg)
    return "Fine. Be that way!" if silent?(msg)
    return "Woah, chill out!" if shouting?(msg)
    return "Sure." if question?(msg)
    "Whatever."
  end

  private

  def silent?(msg) msg == nil || msg.strip.empty? end

  def shouting?(msg) msg == msg.upcase end

  def question?(msg) msg.end_with? "?" end
end
