class Bob
  def hey(statement)
    says = Says.new(statement)
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

class Says < Struct.new(:string)
  def nothing?
    string.nil? || string.empty?
  end

  def question?
    string.end_with?("?")
  end

  def yelling?
    string == string.upcase
  end
end
