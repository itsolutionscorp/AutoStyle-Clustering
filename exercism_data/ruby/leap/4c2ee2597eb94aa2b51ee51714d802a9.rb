class Year

  def initialize( year )
    @year = year
  end

  def leap?
    remainder_evendivby4 = @year % 4
    if remainder_evendivby4 == 0 then
      remainder_evendivby100 = @year % 100
      if remainder_evendivby100 == 0
        remainder_evendivby400 = @year % 400
        unless remainder_evendivby400 == 0
          false
        else
          true
          @year
        end
      else
        true
      end
    else
      false
    end
  end
end
