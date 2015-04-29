require "Prime"

class Prime

  def self.nth(number)
    raise ArgumentError if number == 0
    generator = ::Prime::EratosthenesGenerator.new

    nth_prime = 0.upto(number).reduce do
      generator.next
    end

    nth_prime
  end

end
