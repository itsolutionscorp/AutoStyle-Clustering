class Year 

  def self.leap? year
    is_century?(year) ? is_divisable_by_400?(year) : is_divisable_by_4?(year)
  end

  private 
  def self.is_century? year
    year % 100 == 0
  end

  def self.is_divisable_by_400? year
    year % 400 == 0
  end
  
  def self.is_divisable_by_4? year
    year % 4 == 0
  end

end
