class Year 
  def self.leap?(year)
    return false if (year % 100 == 0 and year % 400 != 0)
    year % 4 == 0 
  end

  # I had this originally, but after nitpicking some other code I realized
  # my problem. This will make the tests pass because it returns true if 
  # a leap year and nil if not.  But the problem is that in ruby methods
  # ending in ? expect the return value to be true or false, so might run into
  # trouble if someone asserted that Year.leap?(1997) == false
=begin
  def self.leap?(year)
    year % 4 == 0 unless (year % 100 == 0 and year % 400 != 0)
  end
=end
end
