class Grains
  def square number, last_value_only=true
    grains = [1]

    number.times {|i| grains << (grains.last * 2) unless i.zero?}

    last_value_only ? grains.last : grains
  end

  def total
    self.square(64, false).inject(:+)
  end
end
