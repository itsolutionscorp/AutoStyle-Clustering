class Year
  def self.leap? (year)
    if year % 4 == 0
      if year % 100 == 0
        if year % 400 == 0
          return true
        end
        return false
      end
      return true
    end
    false
  end
end
