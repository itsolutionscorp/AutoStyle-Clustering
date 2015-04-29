class Responder

  def is_question(saying)
    saying.index('?') == saying.length - 1
  end
  
  def ends_with_exclamation_mark(saying)
      saying.index('!') == saying.length-1
  end
  
  def is_strong_statement(saying)
    saying.index('!') != nil and is_uppercase(saying)
  end

  def is_uppercase(saying)
    b = saying.clone
    b.upcase! == nil
  end
  
  def is_statement(saying)
    not is_question(saying)
  end
  
  def contains_whitesapces_only(saying)
    saying.split(' ').count == 0
  end
end

class Bob

  def initialize
    @responder = Responder.new
  end

  def hey(saying)

    if @responder.contains_whitesapces_only(saying) || saying.length == 0
      return 'Fine. Be that way!'
    end

    if @responder.is_strong_statement(saying) || @responder.is_uppercase(saying)
      return 'Woah, chill out!'
    end
    
    if @responder.is_question(saying)
      return 'Sure.'
    end
    
    if @responder.is_statement(saying)
      return 'Whatever.'
    end

  return nil
  end
end
