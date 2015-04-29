class Bob
  def initialize
  end

  def hey(input)
    case
    when say_nothing(input) then 'Fine, be that way.'
    when shout(input) then 'Woah, chill out!'
    when question(input) then 'Sure.'
    when begin_with_bro(input) then to_leet(input)
    else
      'Whatever.'
    end
  end

  def to_leet(input)
    input.gsub(/[epa]/,'e'=>3,'p' =>'P','a'=>4)
  end

  def say_nothing(input)
    input == ""
  end

  def shout(input)
    input == input.upcase
  end

  def question(input)
    input.end_with?("?")
  end

  def begin_with_bro(input)
    input.match(/^Bro, /) ? to_leet(input) : false      
  end

end
