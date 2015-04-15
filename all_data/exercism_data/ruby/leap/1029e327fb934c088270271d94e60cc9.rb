class Year
  def self.divis_by?(num, year)
    year % num == 0
  end

  def self.leap?(year)
    divis_by?(4, year) && !(divis_by?(100, year) ^ divis_by?(400, year))
  end
end
