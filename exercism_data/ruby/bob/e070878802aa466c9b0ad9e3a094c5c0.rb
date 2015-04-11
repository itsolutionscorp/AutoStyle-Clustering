class Bob
  def hey(msg)
    response = "Woah, chill out!" if !(msg =~ /[a-z]+/) && (msg =~ /[A-Z]+/)
    response ||= "Sure." if msg[-1] == "?"
    response ||= "Fine. Be that way!" if msg.strip.empty?
    response ||= "Whatever."
  end
end
