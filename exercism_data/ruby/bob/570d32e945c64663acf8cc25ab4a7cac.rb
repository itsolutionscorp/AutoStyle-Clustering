class Bob
  def hey(says)
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

class NilClass
  def nothing?
    true
  end
end

class String
  def nothing?
    strip == ""
  end

  def question?
    end_with?("?")
  end

  def yelling?
    self == upcase
  end
end
