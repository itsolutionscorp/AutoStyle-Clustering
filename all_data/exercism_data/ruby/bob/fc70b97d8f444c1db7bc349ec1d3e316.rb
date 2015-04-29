class Bob
  def hey(question)
    [Yell, Question, Silence, Default].each do |interaction|
      interaction.matches?(question) ? (return interaction.answer) : ''
    end
  end
end

class Question
  def self.matches?(question)
    question =~ /^(.+)\?\z/
  end
  
  def self.answer
    "Sure."
  end
end

class Yell
  def self.matches?(question)
    (question =~ /^([^a-z]+)$/) && (question =~ /([A-Z]+)/)
  end
  
  def self.answer
    "Woah, chill out!"
  end
end

class Silence
  def self.matches?(question)
    question =~ /^(\s*)\z/
  end
  
  def self.answer
    "Fine. Be that way!"
  end
end

class Default
  def self.matches?(question)
    true
  end
  
  def self.answer
    "Whatever."
  end
end
