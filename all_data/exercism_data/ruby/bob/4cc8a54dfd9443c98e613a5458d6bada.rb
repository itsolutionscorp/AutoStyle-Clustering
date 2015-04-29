class Bob
  def hey(say)
    if say.to_s.empty?
      "Fine. Be that way."
    elsif say.end_with?("?")
      "Sure."
    elsif say == say.upcase
      "Woah, chill out!"
    else
      "Whatever."
    end
  end
end
