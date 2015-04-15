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
  def initialize(says)
    @says = says 
  end

  def nothing?
    @says.nil? || @says.strip == ""
  end

  def question?
    @says.end_with?("?")
  end

  def yelling?
    @says == @says.upcase
  end
end
