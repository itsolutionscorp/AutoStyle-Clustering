class Year
  def initialize(year)
    @year = year
  end

  def leap?
    four = (@year%4 == 0)
    oneHundred = (@year%100 == 0)
    fourHundred = (@year%400 == 0)

    if(four && oneHundred) 
      if(fourHundred)
       puts "#{@year} is a leap year"
       true
      else
        puts "#{@year} is not a leap year"
        false
      end
    elsif(four && !oneHundred)
      puts "#{@year} is a leap year"
      true
    end
  end
end
