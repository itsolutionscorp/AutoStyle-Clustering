class Year
  def self.leap?(year)
    year = year.to_i
    dividable_by_4 = (year % 4).zero?
    not_dividable_by_100 = !(year % 100).zero?
    dividable_by_400 = (year % 400).zero?
    dividable_by_4 && (not_dividable_by_100 || dividable_by_400)
  end
end
