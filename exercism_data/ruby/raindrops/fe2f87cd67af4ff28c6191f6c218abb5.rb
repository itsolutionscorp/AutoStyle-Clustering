module Raindrops
  CODEBOOK = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong',
  }

  def self.convert(number)
    raindrop = CODEBOOK.collect do |prime,codeword|
      codeword if number % prime == 0
    end.compact.join
    raindrop.empty? ? number.to_s : raindrop
  end
end
