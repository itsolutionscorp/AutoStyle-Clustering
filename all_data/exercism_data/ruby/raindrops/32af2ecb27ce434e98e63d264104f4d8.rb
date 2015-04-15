module Raindrops
  MAPPING = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }

  def Raindrops.divisible_by?(n, divisor)
    n.modulo(divisor).zero?
  end

  def Raindrops.map_the_response(n, mapping)
    mapping.reduce('') do |memo, (divisor, response)|
      if Raindrops.divisible_by?(n, divisor)
        memo + response
      else
        memo
      end
    end
  end

  def Raindrops.convert(n)
    output = Raindrops.map_the_response(n, MAPPING)
    output = n.to_s if output.empty?
    output
  end
end
