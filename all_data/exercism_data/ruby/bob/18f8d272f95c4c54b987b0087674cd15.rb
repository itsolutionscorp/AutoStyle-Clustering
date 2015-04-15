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
    case
    when u.question? then "Sure."
    when u.yell?     then "Woah, chill out!"
    when u.silent?   then "Fine. Be that way!"
    else "Whatever."
    end
  end
end
