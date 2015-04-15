module Year
  extend self

  def leap?(year)
    evenly_divisible = lambda{ |num| year % num == 0 }

    return false unless evenly_divisible[4]
    evenly_divisible[400] || !evenly_divisible[100]
  end

end
