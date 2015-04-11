class Year < Struct.new(:number)
  def leap?
    (divisable_by?(4) && !century?) || divisable_by?(400)
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
