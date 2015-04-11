class Bob

  def hey(voice)
    bob_hears = DetermineVoice.new
    
    if bob_hears.nothing?(voice)
      "Fine. Be that way."
    elsif bob_hears.yelling?(voice)
      "Woah, chill out!"
    elsif bob_hears.questioning?(voice)
      "Sure."
    else
      "Whatever."
    end
  end
end

class DetermineVoice

  def yelling?(voice)
    voice == voice.upcase
  end

  def questioning?(voice)
    voice.end_with? "?"
  end

  def nothing?(voice)
    voice == "" or voice == nil
  end

end
