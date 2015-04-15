class Bob
  def hey(voice)
    bob_hears = DetermineVoice.new(voice)
    
    if bob_hears.nothing?
      "Fine. Be that way."
    elsif bob_hears.yelling?
      "Woah, chill out!"
    elsif bob_hears.questioning?
      "Sure."
    else
      "Whatever."
    end
  end
end

class DetermineVoice
  def initialize(voice)
    @voice = voice.to_s
  end

  def yelling?
    @voice == @voice.upcase
  end

  def questioning?
    @voice.end_with? "?"
  end

  def nothing?
    @voice == ""
  end
end
