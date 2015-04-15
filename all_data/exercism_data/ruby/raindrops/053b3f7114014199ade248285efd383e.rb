# Raindrops
module Raindrops
  @drops = { 3 =>  'Pling', 5 => 'Plang', 7 => 'Plong' }

  def self.prime?(num)
    num == 2 ||  num.odd?
  end

  def self.prime_facts_of(num)
    (1..num).select { |n| num % n == 0 && prime?(n) }
  end

  def self.convert(num)
    drops = prime_facts_of(num).collect { |n| @drops[n] }.compact
    drops.empty? ? num.to_s : drops.join
  end
end
