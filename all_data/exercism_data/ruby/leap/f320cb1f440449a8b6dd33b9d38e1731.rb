class Year
  def initialize year
    @year = year
  end
  
  def leap?
    divisible_by?(4) && not_divisible_by?(100) || divisible_by?(400)
  end

  def not_divisible_by? num
    !divisible_by? num
  end
  def divisible_by? num
    @year % num == 0 
  end

end
__END__
000000100 => 004
001100100 => 100
110010000 => 400
