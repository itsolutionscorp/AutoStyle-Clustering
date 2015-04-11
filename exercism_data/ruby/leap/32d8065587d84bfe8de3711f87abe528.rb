class Year

  def self.leap?(year)
    if (year % 400 == 0)
      true
    elsif (year % 100 == 0)
      false
    elsif (year % 4 == 0)
      true
    end

  end
end
