class Silence
  def self.emotes message
    message.strip == ""
  end
end

class Vociferation
  def self.emotes message
    message == message.upcase
  end
end

class Interrogation
  def self.emotes message
    !!(message.gsub("\n", "") =~ /\?$/)
  end
end

class Confabulation
  def self.emotes message
    true
  end
end

class Recalcitrant
  def self.say
    "Fine. Be that way!"
  end
end

class Remonstratory
  def self.say
    "Woah, chill out!"
  end
end

class Cooperative
  def self.say
    "Sure."
  end
end

class Defiant
  def self.say
    "Whatever."
  end
end

class Bob
  def hey message
    stimuli.each_with_index do |antagonist, response|
      return protagonists response if antagonist.emotes message
    end
  end

  def stimuli
    [Silence, Vociferation, Interrogation, Confabulation]
  end

  def protagonists response
    [Recalcitrant, Remonstratory, Cooperative, Defiant][response].say
  end
end
