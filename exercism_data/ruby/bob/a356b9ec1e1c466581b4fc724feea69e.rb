class Bob
  def hey(string)
    utterance = Utterance.new(string)

    case
    when utterance.empty? then "Fine. Be that way."
    when utterance.question? then "Sure."
    when utterance.shouting? then "Woah, chill out!"
    else
      "Whatever."
    end
  end
end

class Utterance
  attr_reader :string

  def initialize(string)
    @string = String(string)
  end

  def empty?
    string == ""
  end

  def question?
    string.end_with?('?')
  end

  def shouting?
    !empty? && string == string.upcase
  end
end
