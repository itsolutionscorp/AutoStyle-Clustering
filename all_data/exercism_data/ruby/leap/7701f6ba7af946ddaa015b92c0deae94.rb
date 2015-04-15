class Year < Struct.new(:number)
  def leap?
    unless century?
      divisable_by? 4
    else
      divisable_by? 400
    end
  end
  
  def century?
    divisable_by? 100
  end

private
  def divisable_by?(divisor)
    number.multiple_of? divisor
  end
end

class Integer
  def multiple_of?(divider)
    self % divider == 0
  end
end
