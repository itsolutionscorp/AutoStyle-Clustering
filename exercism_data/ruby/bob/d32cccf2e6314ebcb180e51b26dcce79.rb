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

private

  def shouting?(noises)
    !silent_treatment?(noises) && (noises.upcase === noises)
  end

  def asky?(noises)
    noises && noises.end_with?("?")
  end

  def silent_treatment?(noises)
    noises.nil? || noises.strip.empty?
  end
end
