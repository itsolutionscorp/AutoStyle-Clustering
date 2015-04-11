class Year < Struct.new(:number)
  def leap?
    unless century?
      number.multiple_of? 4
    else
      number.multiple_of? 400
    end
  end
  
  def century?
    number.multiple_of? 100
  end
end

class Integer
  def multiple_of?(divider)
    self % divider == 0
  end
end
