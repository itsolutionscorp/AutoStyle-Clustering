class Year
  def self.leap?(year)
    year.modulo(4) == 0 && year.modulo(100) != 0 || year.modulo(400) == 0
  end
end
