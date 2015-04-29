require 'prime'

class Raindrops
  def self.convert(input)
    factors = Prime.prime_division(input)
    mapping =  { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }

    output = ""

    factors.each do |prime, _|
      value = mapping[prime] || next
      output << value
      mapping.delete(prime)
    end

    output == "" ? input.to_s : output
  end
end
