class Year

  def self.leap?(year)
    year = year.to_i

    if div_by_4?(year)
      if div_by_400?(year)
        return true
      elsif div_by_100?(year)
        return false
      else
        return true
      end
    else
      return false
    end

  end

  def self.div_by_4?(year)
    year % 4 == 0
  end

  def self.div_by_400?(year)
    year % 400 == 0
  end

  def self.div_by_100?(year)
    year % 100 == 0
  end
end
