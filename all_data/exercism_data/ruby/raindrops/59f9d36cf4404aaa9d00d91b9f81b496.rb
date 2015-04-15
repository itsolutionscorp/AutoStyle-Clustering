module Raindrops
  PRIME_WORDS = {
    3 => "Pling",
    5 => "Plang",
    7 => "Plong"
  }.freeze

  def self.convert(number)
    words = PRIME_WORDS.map { |prime, word| word if (number % prime).zero? }
    words.any? ? words.join : number.to_s
  end
end
