class Bob

  def hey(said_to_bob)
    return "Woah, chill out!" if said_to_bob == said_to_bob.upcase unless said_to_bob == said_to_bob.downcase
    return "Sure." if said_to_bob[-1] == "?"
    return "Fine. Be that way!" if said_to_bob.strip.empty?
    "Whatever."
  end
end
