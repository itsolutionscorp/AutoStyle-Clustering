class Year
  def self.leap?(year)
    if year.modulo(100).zero?
      year.modulo(4).zero? and year.modulo(400).zero?
    else
      year.modulo(4).zero?
    end
  end
end
