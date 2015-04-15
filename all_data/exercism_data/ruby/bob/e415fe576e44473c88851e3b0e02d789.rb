class Bob

  def hey(sentence)
    klass = klassify(sentence)
    klass.call
  end

  private
  def klassify(sentence)
    match = class_map.select { |(proc, klass)| proc.call(sentence) }
    match.empty? ? Whatever : match.values.first
  end

  def class_map
    {
      -> (s) { !s.strip.scan(/[A-Z]/).empty? && s.strip.scan(/[A-Z]/)  == s.strip.scan(/[a-zA-Z]/) } => Yell,
      -> (s) { s.end_with?("?") } => Question,
      -> (s) { s.strip.empty? } => Silence,
    }
  end
end

class Yell
  def self.call
    "Woah, chill out!"
  end
end

class Question
  def self.call
    "Sure."
  end
end

class Silence
  def self.call
    "Fine. Be that way!"
  end
end

class Whatever
  def self.call
    "Whatever."
  end
end
