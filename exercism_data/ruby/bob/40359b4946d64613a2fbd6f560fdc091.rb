class Utterance
  def initialize(string)
    @utterance = string.to_s
  end

  def question?
    @utterance.end_with?("?") && !yell?
  end

  def yell?
    @utterance.match(/[A-Z]/) && @utterance.upcase == @utterance
  end

  def silent?
    @utterance.empty?
  end
end

class Bob
  def hey(string)
    u = Utterance.new(string)
    return "Sure."              if u.question?
    return "Woah, chill out!"   if u.yell?
    return "Fine. Be that way!" if u.silent?
    return "Whatever."
  end
end
