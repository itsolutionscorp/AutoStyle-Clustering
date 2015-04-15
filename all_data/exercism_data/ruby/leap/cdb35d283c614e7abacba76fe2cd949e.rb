class Year < Struct.new(:value)
  def leap?
    (value % 4 == 0) and 
    not(value % 100 == 0) or
    (value % 400 == 0)
  end
end
