class Bob

  def hey(string)
    case Phrase.new string
    when :yelled?.to_proc
      "Woah, chill out!"
    when :question?.to_proc
      "Sure."
    when :silence?.to_proc
      "Fine. Be that way!"
    else 
      "Whatever."
    end
  end

end

class Phrase
  attr_reader :string

  def initialize(string)
    @string = string.to_s
  end

  def yelled?
    !silence? && string == string.upcase
  end

  def question?
    string.end_with?('?')
  end

  def silence?
    string.empty?
  end

end
