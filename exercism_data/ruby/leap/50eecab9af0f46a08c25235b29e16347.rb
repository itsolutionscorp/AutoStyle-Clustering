class Year
  def self.leap?(year)
    if year % 4 == 0
      if year % 100 == 0 
        if year % 400 == 0
          "Yes, #{year} is a leap year"
        end
      else
        "Yes, #{year} is a leap year"
      end
    end
  end
end
