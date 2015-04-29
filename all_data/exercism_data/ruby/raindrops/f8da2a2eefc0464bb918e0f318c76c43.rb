require 'prime'

class Raindrops

  OUTPUTS = {"Pling" => 3, "Plang" => 5, "Plong" => 7}

  def self.convert(original_number)
    determine_ouput(find_primes(original_number)) || original_number.to_s
  end

  def self.determine_ouput(primes)
    output = primes.inject("") {|output,number| output += OUTPUTS.key(number).to_s}

    output.empty? ? nil : output
  end

  def self.find_primes(number)
    number.prime_division.flatten
  end
end
