class Bob
  def hey(adult_noises)
    if shouting? adult_noises
      "Woah, chill out!"
    elsif asky? adult_noises
      "Sure."
    elsif silent_treatment? adult_noises
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end

  def shouting?(noises)
    /^(?<no_lower_case>[^a-z]+)$/.match(noises) && !silent_treatment?(noises)
  end

  def asky?(noises)
    /\?$/.match(noises)
  end

  def silent_treatment?(noises)
    /^(?<no_actual_phonemes>[^a-zA-Z]*)$/.match(noises) || noises.nil?
  end
end
