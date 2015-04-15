class Raindrops

  def convert(number)
    result = ""
    result << "Pling" if number.divides_evenly_by(3)
    result << "Plang" if number.divides_evenly_by(5)
    result << "Plong" if number.divides_evenly_by(7)
    result = number.to_s if result.empty?
    result
  end

end

class Fixnum

  def divides_evenly_by(divisor)
    self % divisor == 0
  end

end
