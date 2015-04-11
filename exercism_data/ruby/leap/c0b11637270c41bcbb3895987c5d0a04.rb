ModuloFriend = Struct.new :number do
  def is_evenly_divisible_by? divisor
    number % divisor == 0
  end

  def is_not_evenly_divisible_by? divisor
    !is_evenly_divisible_by? divisor
  end

  def self.for number
    return self.new number
  end
end

module Year
  def self.leap? year_number
    year = ModuloFriend.for year_number

    (year.is_evenly_divisible_by? 4) and (
      (year.is_not_evenly_divisible_by? 100) or
        (year.is_evenly_divisible_by? 400)
    )
  end
end
