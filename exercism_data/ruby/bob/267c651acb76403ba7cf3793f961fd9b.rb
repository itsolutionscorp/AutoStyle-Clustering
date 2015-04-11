class Bob

  def hey input
    
    talking = Phrase.new(input)

    if talking.silent?
      'Fine. Be that way.'
    elsif talking.yelling?
      'Woah, chill out!'
    elsif talking.questioning?
      'Sure.'
    else
      'Whatever.'
    end
  
  end

end

class Phrase

  def initialize input
    @statement = input
  end

  def silent?
    @statement.nil? || @statement.empty? 
  end

  def yelling?
    @statement == @statement.upcase  
  end

  def questioning?
    @statement.end_with?('?') 
  end

end
