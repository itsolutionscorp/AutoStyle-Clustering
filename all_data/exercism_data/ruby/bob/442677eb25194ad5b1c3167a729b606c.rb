class Bob

  def hey input
    
    talking = Phrase.new(input)

    return 'Fine. Be that way.' if talking.silent?      
    return 'Woah, chill out!' if talking.yelling?
    return 'Sure.' if talking.questioning?
    else 'Whatever.'
  
  end

end

class Phrase

  attr_reader :statement

  def initialize input
    @statement = input
  end

  def silent?
    statement.nil? || @statement.empty? 
  end

  def yelling?
    statement == @statement.upcase  
  end

  def questioning?
    statement.end_with?('?') 
  end

end
