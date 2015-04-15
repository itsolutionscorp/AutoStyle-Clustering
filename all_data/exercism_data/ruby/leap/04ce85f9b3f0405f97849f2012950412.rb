
module Year
  def self.leap? year
    if year % 4 == 0
      return true if not year % 100 == 0
      return true if     year % 400 == 0
    end
    false
  end
end
