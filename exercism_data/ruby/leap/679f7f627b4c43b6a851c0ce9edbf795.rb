class Year
  def self.leap?(year, yesno = 'No')
    unless year % 100 == 0 && year % 400 != 0
      yesno = 'Yes' if year % 4 == 0
    end

    "#{yesno}, #{year} is a leap year"
  end


end
