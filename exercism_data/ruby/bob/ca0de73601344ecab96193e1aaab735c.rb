# Indented using 2 spaces

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
    @statement == '' || @statement == nil ? true : false
  end

  def yelling?
    @statement == @statement.upcase ? true : false
  end

  def questioning?
    @statement.end_with?('?') ? true : false
  end

end
