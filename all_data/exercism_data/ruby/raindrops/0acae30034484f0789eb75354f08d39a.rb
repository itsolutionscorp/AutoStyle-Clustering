class Raindrops

  def convert(number)
    result = word_num.select do |k, v|
      v.call(number)
    end.map { |k,_| k}.join
    result = number.to_s if result.empty?
    result
  end

  def word_num
    {
      "Pling" => ->(number) { number.divides_evenly_by(3) },
      "Plang" => ->(number) { number.divides_evenly_by(5) },
      "Plong" => ->(number) { number.divides_evenly_by(7) }
    }
  end

end

class Fixnum

  def divides_evenly_by(divisor)
    self % divisor == 0
  end

end
