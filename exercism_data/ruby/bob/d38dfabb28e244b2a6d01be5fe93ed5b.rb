class Bob
  def hey(msg)
    if silent? msg
      return "Fine. Be that way!"
    elsif question? msg
      return "Woah, chill out!"
    elsif shouting? msg
      return "Sure."
    end
    "Whatever."
  end

  private

  def silent?(msg) msg == nil || msg.strip == "" end

  def question?(msg) msg == msg.upcase end

  def shouting?(msg) msg[-1] == "?" end
end
