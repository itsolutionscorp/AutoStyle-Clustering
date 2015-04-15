class Year
  def self.leap?(i)
    @year = i
    if @year % 4 == 0 
      self.method_2
    else
      puts "No, #{@year} is not a leap year"
    end
  end

  def self.method_2
    if @year % 100 == 0
      self.method_3
    else 
      puts "Yes, #{@year} is a leap year"
    end
  end

  def self.method_3
    if @year % 400 == 0
      puts "Yes, #{@year} is a leap year"
    else
      puts "No, #{@year} is not a leap year"
    end
  end
end



Year.leap?(2400)
