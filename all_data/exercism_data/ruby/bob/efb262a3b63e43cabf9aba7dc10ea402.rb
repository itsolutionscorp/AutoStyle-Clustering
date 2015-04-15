class Bob
  def hey(input)
    case input
    when :nil?, :silence?
      'Fine. Be that way!'
    when :shouting?
      'Woah, chill out!'
    when :question?
      'Sure.'
    else
      'Whatever.'
    end
  end  
end

class String
  def silence?
    self !~ /\S/
  end
  def shouting?
    self == self.upcase
  end

  def question?
    end_with?("?")
  end
end

class Symbol
  alias :original_triple_equals :"==="

  def ===(object)
    original_triple_equals(object) ||
      (object.respond_to?(self) && object.__send__(self))
  end

end
