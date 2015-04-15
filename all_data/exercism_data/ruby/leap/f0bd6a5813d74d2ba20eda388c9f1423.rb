class Year
  def self.leap?(year)
    if (year % 4) == 0
      if (year % 400) == 0
        true
      elsif (year % 100) == 0
        false
      else
        true
      end
    end
  end
end
