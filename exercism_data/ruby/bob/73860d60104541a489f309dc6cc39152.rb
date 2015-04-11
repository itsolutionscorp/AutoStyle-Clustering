class Bob
  def hey(says_string)
    says = Says.new(says_string)
    case
    when says.nothing?
      "Fine. Be that way."
    when says.yelling?
      "Woah, chill out!"
    when says.question?
      "Sure."
    else
      "Whatever."
    end
  end
end

class Says
  def initialize(string)
    @string = string 
  end

  def nothing?
    @string.nil? || @string.strip == ""
  end

  def question?
    @string.end_with?("?")
  end

  def yelling?
    @string == @string.upcase
  end
end
