class Year
  def self.leap? year
    if year % 4 == 0 
      return true if year % 400 == 0 || year % 100 != 0
    end
    false
  end
end
