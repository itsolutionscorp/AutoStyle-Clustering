class Year
  require 'Date'
  def leap?(year)
    dc = Date.new(year,2,28)
    dc = dc + 1
    out = false
    out = true if dc.month == 2
    out
  end
end
