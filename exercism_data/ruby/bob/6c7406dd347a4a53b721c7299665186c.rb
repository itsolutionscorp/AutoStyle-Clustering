class Bob

  def hey(input)
    if question?(input)
      'Sure'
    elsif yell?(input)
      'Woah, chill out!'
    elsif say_nothing?(input)
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end


  private
  def question?(input)
    input.end_with?('?')
  end

  def yell?(input)
   input.upcase == input
  end

  def say_nothing?(input)
    input.empty?
  end
end
