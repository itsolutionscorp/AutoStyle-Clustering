require 'prime'
require 'pp'

class Raindrops

  def self.convert(num)
    output = ""
    prime_facts = num.prime_division.flatten & [3, 5, 7]
    output = num.to_s if prime_facts.empty?
    prime_facts.each { |i|
      output += "Pling" if i == 3
      output += "Plang" if i == 5
      output += "Plong" if i == 7
    }
    output
  end

end
