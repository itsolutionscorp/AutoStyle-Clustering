class Bob
  def hey(say)
    if say == say.upcase
      return "Woah, chill out!"
    elsif say[-1].include?("?")
      return "Sure."
    elsif say.empty? || say.nil?
      return "Fine. Be that way."
    else
      return "Whatever."
    end
  end
end
