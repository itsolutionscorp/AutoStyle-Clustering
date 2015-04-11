class Year
  def self.leap?(year, answer = 'No')
    unless year % 100 == 0 && year % 400 != 0
      answer = 'Yes' if year % 4 == 0
    end

    "#{answer}, #{year} is a leap year"
  end
end
