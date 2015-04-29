class Year
  def self.leap?(year)
    if year % 4 == 0
      if year % 100 == 0
        year % 400 == 0
      else
        true
      end
    end
  end
end
