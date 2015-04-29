class Year
  def initialize year
    @year = year
  end
  
  def leap?
    if divisible_by?(4)
      if divisible_by?(100)
        return divisible_by?(400)
      else
        return true
      end
    end
  end

  def divisible_by? num
    @year % num == 0 
  end

end
__END__
000000100 => 004
001100100 => 100
110010000 => 400
