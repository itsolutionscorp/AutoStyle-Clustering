class Year
  def self.leap?(year)
    if year.modulo(4).zero?
      return true unless year.modulo(100).zero? && !year.modulo(400).zero?
    end
  end
end
