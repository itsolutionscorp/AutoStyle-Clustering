class Bob
  def hey(utterance)
    utterance = Utterance.new(utterance)

    case
    when utterance.question? then "Sure."
    when utterance.shouting? then "Woah, chill out!"
    when utterance.silent? then "Fine. Be that way."
    else "Whatever."
    end
  end
end

class Utterance
  attr_reader :string

  def initialize(string)
    @string = String(string)
  end

  def silent?
    string == ""
  end

  def question?
    string.end_with?('?')
  end

  def shouting?
    !silent? && string == string.upcase
  end
end
