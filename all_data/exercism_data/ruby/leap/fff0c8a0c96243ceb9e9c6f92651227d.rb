class Year
  def self.leap?(year)
    return true if year.modulo(400).zero?
    return true if year.modulo(4).zero? && year.modulo(100).nonzero?  
    return false
  end
end
