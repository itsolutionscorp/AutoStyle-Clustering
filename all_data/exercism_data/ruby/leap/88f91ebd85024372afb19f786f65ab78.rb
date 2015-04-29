class Year
  def self.leap?(year)
    year.modulo(4).zero? and year.modulo(100).nonzero? or year.modulo(400).zero?
  end
end
