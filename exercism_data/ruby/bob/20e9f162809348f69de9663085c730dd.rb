class Bob
  def hey(msg)
    return "Fine. Be that way!" if silent?(msg)
    return "Woah, chill out!" if question?(msg)
    return "Sure." if shouting?(msg)
    "Whatever."
  end

  private

  def silent?(msg) msg == nil || msg.strip == "" end

  def question?(msg) msg == msg.upcase end

  def shouting?(msg) msg[-1] == "?" end
end
