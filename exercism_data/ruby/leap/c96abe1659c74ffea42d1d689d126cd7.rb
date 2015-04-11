# Leap year determination class
class Year
  def self.leap?( year )
    leap_year?( year )  && leap_century?( year )
  end

  private

  def self.leap_year?( year )
    year % 4 == 0
  end

  def self.leap_century?( year )
    year % 100 != 0 || year % 400 == 0
  end
end
