class Year
  def self.leap?(test_year)
    if test_year%100==0 && test_year%400!=0
      false
    elsif test_year%4==0
      true
    else
      false
    end
  end
end
