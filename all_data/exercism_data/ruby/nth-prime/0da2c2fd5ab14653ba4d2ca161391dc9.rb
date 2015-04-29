require 'prime'

class Prime

  def self.nth ordinal
    raise ArgumentError.new('Invalid argument') if ordinal < 1

    sieve = Prime::EratosthenesGenerator.new
    prime = 0
    ordinal.times { prime = sieve.next  }
    prime
  end

end
