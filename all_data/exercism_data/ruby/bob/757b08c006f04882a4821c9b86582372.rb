class Bob
  def hey(say)
    if say == say.upcase && say =~/[a-zA-Z]/
      "Woah, chill out!"
    elsif say =~ /\?\z/
      "Sure."
    elsif say.empty? || say =~ /\A\s+\z/
      'Fine. Be that way!'
    else
      "Whatever."
    end
  end
end
