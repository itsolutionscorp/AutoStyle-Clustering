class Year
  def self.leap?(year)
    div_by = ->(x) { (year % x).zero? }
    div_by[400] || div_by[4] && !div_by[100]
  end
end
