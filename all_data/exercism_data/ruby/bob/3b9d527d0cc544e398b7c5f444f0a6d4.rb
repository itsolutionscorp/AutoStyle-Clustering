class Bob
  def hey(interaction)
    return 'Fine. Be that way!' if considers_silent?(interaction)
    return 'Woah, chill out!' if considers_shouty?(interaction)
    return 'Sure.' if considers_question?(interaction)
    "Whatever."
  end

  def considers_shouty?(interaction)
    return false if interaction.upcase == interaction.downcase

    interaction.upcase == interaction
  end

  def considers_question?(interaction)
    interaction.end_with? '?'
  end

  def considers_forceful?(interaction)
    interaction.end_with? '!'
  end

  def considers_silent?(interaction)
    interaction.strip.empty?
  end
end
