class Bob
  def hey(say)
    if say.to_s.length == 0
       "Fine. Be that way."
    elsif say[-1].include?("?")
       "Sure."
    elsif say == say.upcase
       "Woah, chill out!"
    else
       "Whatever."
    end
  end
end
