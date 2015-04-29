# Raindrops
module Raindrops
  @drops = { 3 =>  'Pling', 5 => 'Plang', 7 => 'Plong' }

  def prime?(num)
    num == 2 || num.odd?
  end

  def self.facts_of(num)
    (1..num).select { |n| prime?(num) && num % n == 0  }
  end

  def self.convert(num)
    drops = facts_of(num).collect { |n| @drops[n] }
    drops.compact.empty? ? num.to_s : drops.join
  end
end
