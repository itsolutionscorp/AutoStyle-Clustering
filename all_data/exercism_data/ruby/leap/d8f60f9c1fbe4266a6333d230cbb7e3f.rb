require 'debugger'
class Year
  EXCEPTIONAL_CENTURY = 400
  CENTURY = 100
  ELECTION_YEAR = 4
  def initialize(year)
    @year = year
  end

  def leap?
    return true if (@year % EXCEPTIONAL_CENTURY).zero?
    return false if (@year % CENTURY).zero?
    return true if (@year % ELECTION_YEAR).zero?
    return false
  end
end
