class Year
  def self.leap?(y)
    if y % 4 != 0
      return false
    elsif y % 400 == 0 
      return true
    elsif y % 100 == 0
      return false
    end

    true
  end
end
