module Year
  extend self

  def leap?(year)
    evenly_divisibly = lambda{ |num| year % num == 0 }

    return false unless evenly_divisibly[4]
    evenly_divisibly[400] || !evenly_divisibly[100]
  end

end
