class Bob
  def hey(say)
    if say == say.upcase
      return "Woah, chill out!"
    elsif say.include?("?")
      return "Sure."
    elsif say.length == 0
      return ""
    else
      return "Whatever."
    end
  end
end
