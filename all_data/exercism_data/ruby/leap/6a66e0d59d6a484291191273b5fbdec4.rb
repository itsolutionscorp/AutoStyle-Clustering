class Year
  def self.leap?(year)
    truthiness = false
    truthiness = !truthiness if year % 4 == 0 
    truthiness = !truthiness if year % 100 == 0 
    truthiness = !truthiness if year % 400 == 0 
    truthiness ? "Yes, #{year} is a leap year" : "No, #{year} is not a leap year"
  end
end
