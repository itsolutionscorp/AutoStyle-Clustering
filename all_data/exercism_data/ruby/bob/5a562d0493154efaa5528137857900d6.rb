class Bob
  def hey(say)
    if say.strip.empty?
      'Fine. Be that way!'
    elsif say == say.upcase && say.index(/[A-Z]/)
      'Woah, chill out!'
    elsif say.end_with?('?')
      'Sure.'
    else
      'Whatever.'
    end
  end
end
