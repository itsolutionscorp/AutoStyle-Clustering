class Bob
  def hey(message)
    [Yell, Question, Silence, Default].each do |interaction|
      interaction.matches?(message) ? (return interaction.answer) : ''
    end
  end
end

class Question
  def self.matches?(message)
    message =~ /^(.+)\?\z/
  end
  
  def self.answer
    "Sure."
  end
end

class Yell
  def self.matches?(message)
    (message =~ /^([^a-z]+)$/) && (message =~ /([A-Z]+)/)
  end
  
  def self.answer
    "Woah, chill out!"
  end
end

class Silence
  def self.matches?(message)
    message =~ /^(\s*)\z/
  end
  
  def self.answer
    "Fine. Be that way!"
  end
end

class Default
  def self.matches?(message)
    true
  end
  
  def self.answer
    "Whatever."
  end
end
