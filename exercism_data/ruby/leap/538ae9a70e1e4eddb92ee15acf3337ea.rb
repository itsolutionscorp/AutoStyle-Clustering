# I can't believe anyone would cheat on a task this simple...
class Year
  def self.leap?( year )
    year % 4 == 0 && leap_century?( year )
  end

  def self.leap_century?( year )
    year % 100 != 0 || year % 400 == 0
  end
end
