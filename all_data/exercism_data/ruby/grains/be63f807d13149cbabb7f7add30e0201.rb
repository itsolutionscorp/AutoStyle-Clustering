class Grains 

  TOTAL = 18446744073709551615


  def square(number)
    1 * 2**(number-1)
  end

  def total
    # sum = 0              this method can be used to calculate the actual number but since it is a fixed board I think that it is safe to just save the number in a constant;       
    # 64.times do |i|
    #   sum += square(i + 1)
    # end
    # sum

    TOTAL
  end

end
