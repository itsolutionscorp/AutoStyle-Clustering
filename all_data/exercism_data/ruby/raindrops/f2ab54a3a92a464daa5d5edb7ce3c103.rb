require 'prime'

module Raindrops
  CODEBOOK = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong',
  }

  def self.convert(number)
    raindrop = Prime.prime_division(number).collect do |prime,_|
      CODEBOOK[prime] if CODEBOOK.include?(prime)
    end.compact.join
    raindrop.empty? ? number.to_s : raindrop
  end
end
