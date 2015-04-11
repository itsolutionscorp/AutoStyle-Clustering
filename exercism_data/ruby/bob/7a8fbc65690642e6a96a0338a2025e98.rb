class Bob
  def hey(say_to_bob)
    if say_to_bob.nil? || say_to_bob.empty?
      'Fine. Be that way.'
    elsif say_to_bob.upcase.eql?(say_to_bob)
      'Woah, chill out!'
    elsif say_to_bob.end_with?('?')
      'Sure.'
    else
      'Whatever.'
    end
  end
end
