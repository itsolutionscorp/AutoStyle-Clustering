class Year

  def self.leap? year
    @@year = year
    by_400? || (by_4? && not_by_100)
  end

  def self.by_4?
    @@year % 4 == 0
  end

  def self.not_by_100
    @@year % 100 != 0
  end

  def self.by_400?
    @@year % 400 == 0
  end

end
