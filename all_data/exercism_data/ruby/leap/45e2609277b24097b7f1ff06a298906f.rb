module Year
  def self.leap?(year)
    year.modulo(400).zero? || !year.modulo(100).zero? && year.modulo(4).zero?
  end
end

class Integer
  ##
  # Returns +self+ if it is a leap year, +nil+ otherwise.
  #
  #   (1895..1905).map(&:leap_year?)
  #   # => [nil, 1896, nil, nil, nil, nil, nil, nil, nil, 1904, nil]
  #
  def leap_year?
    self if Year.leap?(self)
  end
end
