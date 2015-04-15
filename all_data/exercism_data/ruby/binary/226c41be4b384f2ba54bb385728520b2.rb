class Binary
  def initialize(i)
  @binary = i
  end

  def to_decimal
    nth = @binary.to_s.size
    n_1 = nth -1
    string = @binary.to_s
    array = string.split(//)
    array1 = array.collect{|i| i.to_i}
    array2 = []

    begin
      array2 << n_1
      n_1 -= 1;
    end until n_1 == -1
    
    add_together = []
    zipped_arrays = array1.zip(array2)

    zipped_arrays.each do |x,y|
      add_together << x * (2** y)
      end
    to_return = add_together.inject { |mem, var| mem + var }

    if string=~/\D/
     0
    else 
      to_return
    end
  end

end


#Binary.new('101').to_decimal
