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

class Says < Struct.new(:statement)
  def nothing?
    statement.nil? || statement.empty?
  end

  def question?
    statement.end_with?("?")
  end

  def yelling?
    statement == statement.upcase
  end
end
