class Year
  def self.leap?(year)
    year.modulo(400).zero? || ( year.modulo(4).zero? && !year.modulo(100).zero? )
  end
end
