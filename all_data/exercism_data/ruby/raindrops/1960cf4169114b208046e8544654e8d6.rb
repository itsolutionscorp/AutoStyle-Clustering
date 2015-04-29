class Raindrops

  def self.convert(int)
    if int == 1
      return '1'
    end
    ar = []
    array = converter(int, 0, ar)
    return array
  end

  def converter(int, current, ar)
    if current = 1;
      ar << current
    elsif int % current == 0
      ar << current
    else
      converter(int, current-2, ar)
    end
  end

end
