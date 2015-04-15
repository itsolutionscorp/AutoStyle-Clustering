require 'prime'

class Raindrops
  def self.convert(input)
    mapping =  { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }
    factors = Prime.prime_division(input)

    output = ""

    factors.each do |prime, _|
      output << (mapping[prime] || next)
    end

    output == "" ? input.to_s : output
  end
end
