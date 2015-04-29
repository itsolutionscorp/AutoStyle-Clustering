class Year 

  def self.leap? year
    century?(year) ? div_by_400?(year) : div_by_4?(year)
  end

  private 
  def self.century? year
    year % 100 == 0
  end

  def self.div_by_400? year
    year % 400 == 0
  end
  
  def self.div_by_4? year
    year % 4 == 0
  end

end
