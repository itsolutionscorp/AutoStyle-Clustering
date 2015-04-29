require 'date'

class Year

  def self.leap?(year_number)
    (year_number % 400 == 0) ||
      ((year_number % 4 == 0) && (year_number % 100 != 0))
  end

end
