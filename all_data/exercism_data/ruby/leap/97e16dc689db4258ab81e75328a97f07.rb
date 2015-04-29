module Year
  def self.leap?(year)
    return true if (year.modulo(4).zero? and not year.modulo(100).zero?) or year.modulo(400).zero?
    false
  end
end
