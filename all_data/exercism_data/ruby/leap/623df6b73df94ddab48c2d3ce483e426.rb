class Year
  def self.leap?(yr)
    return TRUE if yr%400==0
    return FALSE if yr%100==0
    return TRUE if yr%4==0
  end
end
