class Year
  def self.leap?(n)
    if n%400 == 0
      true
    elsif n%100 == 0
      false
    elsif n%4 == 0
      true
    else
      false
    end
  end
end
