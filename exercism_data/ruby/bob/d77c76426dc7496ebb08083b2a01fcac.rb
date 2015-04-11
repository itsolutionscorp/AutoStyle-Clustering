class Bob
  def hey(words)
    says = Statement.new(words)
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

Statement = Struct.new(:statement) do
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
