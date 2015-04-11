class Bob
  
  def hey(anything)
    case statement = Statement.new(anything)
    when statement.is_empty?
      'Fine. Be that way!'
  	when statement.is_yelling? 
  		'Woah, chill out!'
    when is_a_polite_question?  
      'Sure.'
  	else
  	  "Whatever."
  	end
  end

end

class Statement

  def initialize(statement)
    @statement = statement
  end

  def is_empty?
    @statement.strip.empty?
  end

  def  is_yelling?
    @statement == anything.upcase
  end

  def is_a_polite_question?
    @statement.end_with?('?') 
  end

end
