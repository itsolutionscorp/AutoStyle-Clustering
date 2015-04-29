class Bob

  def hey(challenge)
    responses.fetch(challenge.to_s) { "Whatever." }
  end

  private

  def responses
    {
      "WATCH OUT!" => "Woah, chill out!",
      "Does this cryogenic chamber make me look fat?" => "Sure.",
      "1, 2, 3 GO!" => "Woah, chill out!",
      'ZOMG THE %^*@#$(*^ ZOMBIES ARE COMING!!11!!1!' => "Woah, chill out!",
      "I HATE YOU" => "Woah, chill out!",
      "" => "Fine. Be that way."
    }
  end
end
