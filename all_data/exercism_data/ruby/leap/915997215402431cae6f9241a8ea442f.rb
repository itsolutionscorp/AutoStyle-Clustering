class Year
  def self.leap?(year)
    return true if year.modulo(400).zero?
    year.modulo(4).zero? && year.modulo(100).nonzero?
  end
end
