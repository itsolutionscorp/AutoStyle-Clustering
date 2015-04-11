class Year
  def self.leap?(year)
    true if year.modulo(4) == 0 && year.modulo(100) != 0 || year.modulo(400) == 0
  end
end
