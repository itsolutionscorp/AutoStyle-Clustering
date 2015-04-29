class Year
  def self.leap?(year)
    { 400 => true, 100 => false, 4 => true }.each do |y, v|
      return v if year % y == 0
    end
    false
  end
end
