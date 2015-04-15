class Year
  @@leap = false 
  def initialize(year)
    if year % 4 == 0 
      @@leap = true 
    end 
    if year % 100 == 0
      @@leap = false
    end
    if year % 400 == 0
      @@leap = true
    end

  end

  def leap?
    @@leap
  end
end
