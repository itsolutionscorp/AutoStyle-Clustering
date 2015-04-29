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
    @voice = voice
  end

  def yelling?
    @voice == @voice.upcase
  end

  def questioning?
    unless @voice.nil?
      @voice.end_with? "?"
    end
  end

  def nothing?
    @voice.nil? or @voice.empty?
  end
end
