require 'Prime'

class Raindrops
  MESSAGES = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }

  def self.convert(n)
    m = Prime.prime_division(n).map { |f| MESSAGES[f.first] }.join
    m.empty? ? n.to_s : m
  end
end
