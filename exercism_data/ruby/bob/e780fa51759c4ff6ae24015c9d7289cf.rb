class Bob
  def hey(input)
    @input = input

    if said_nothing
     'Fine. Be that way!'
    elsif yell
      'Woah, chill out!'
    elsif question
      'Sure.'
    else 
      'Whatever.'	
    end
  end

  private
  
  def said_nothing
    @input.nil? || @input == '' 
  end

  def yell
    @input.upcase == @input
  end

  def question
    @input[-1] == '?' 
  end
end
