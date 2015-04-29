class Year
  def self.leap?(year)
   ( self.div_by?(year, 4) && !self.div_by?(year, 100) ) || self.div_by?(year, 400)
  end

  def self.div_by?(year, number)
    (year % number == 0)
  end

end
