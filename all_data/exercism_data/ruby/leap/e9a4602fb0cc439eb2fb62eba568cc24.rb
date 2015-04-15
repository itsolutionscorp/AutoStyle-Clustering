class Year
  def self.leap?(year)
    divided_by_4?(year) && !(divided_by_100?(year) && !divided_by_400?(year)) 
  end

  def self.divided_by_4?(num)
    num % 4 == 0
  end

  def self.divided_by_100?(num)
    num % 100 == 0
  end

  def self.divided_by_400?(num)
    num % 400 == 0
  end
end
