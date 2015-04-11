class Raindrops
  def convert(number)
    number.to_raindrop || number.to_s
  end
end

class Fixnum
  def to_raindrop
    output = ''
    output += 'Pling' if evenly_divisible_by(3)
    output += 'Plang' if evenly_divisible_by(5)
    output += 'Plong' if evenly_divisible_by(7)
    return output unless output.empty?
  end

  private

  def evenly_divisible_by(divisor)
    self % divisor == 0
  end
end
