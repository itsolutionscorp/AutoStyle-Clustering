class Year < Struct.new(:number)
  def leap?
    (has_factor?(4) && !century?) || has_factor?(400)
  end
  
  def century?
    has_factor? 100
  end

private
  def has_factor?(divisor)
    number % divisor == 0
  end
end
