class Year

  def self.leap?(year)
    if century?(year)
      year % 400 == 0
    else
      year % 4 == 0
    end
  end

  def self.century?(year)
    year % 100 == 0
  end
  
end
