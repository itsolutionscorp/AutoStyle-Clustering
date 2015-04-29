class Year

  def self.leap?(year)
    if year % 400 == 0
      true
    elsif year % 4 == 0
      true unless year % 100 == 0
    end
  end

end
