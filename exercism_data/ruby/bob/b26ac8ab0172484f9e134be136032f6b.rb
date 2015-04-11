class Bob
  def hey stimuli
    antagonists.each_with_index do |antagonist, response|
      return protagonist response if antagonist.emotes stimuli
    end
  end

  def antagonists
    [Silence, Vociferation, Interrogation, Confabulation]
  end

  def protagonist response
    [Recalcitrant, Remonstratory, Cooperative, Defiant][response].say
  end
end

class Silence
  def self.emotes stimuli
    stimuli.strip == ""
  end
end

class Vociferation
  def self.emotes stimuli
    stimuli == stimuli.upcase
  end
end

class Interrogation
  def self.emotes stimuli
    !!(stimuli.gsub("\n", "") =~ /\?$/)
  end
end

class Confabulation
  def self.emotes stimuli
    true
  end
end

class Recalcitrant
  def self.say; "Fine. Be that way!"; end
end

class Remonstratory
  def self.say; "Woah, chill out!"; end
end

class Cooperative
  def self.say; "Sure."; end
end

class Defiant
  def self.say; "Whatever."; end
end
