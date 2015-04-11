class Remark
  attr_reader :string

  def initialize(string)
    @string = string.strip
  end

  def shouting?
    string.upcase == string && contains_letters?
  end

  def silence?
    string.empty?
  end

  def question?
    string.end_with?('?')
  end

  private

  def contains_letters?
    string =~ /[a-z]/i
  end
end

class Bob
  def hey(utterance)
    remark = Remark.new(utterance)
    case
    when remark.shouting?
      "Whoa, chill out!"
    when remark.silence?
      "Fine. Be that way!"
    when remark.question?
      "Sure."
    else
      "Whatever."
    end
  end
end
