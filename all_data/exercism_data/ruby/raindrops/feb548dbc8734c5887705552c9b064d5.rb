require 'prime'

class Raindrops

  NUM_TO_WORD = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }

  def self.convert(number)
    prime_numbers = Raindrops.prime_factorization(number)
    words = prime_numbers.map{|num| NUM_TO_WORD[num]}.uniq.compact
    if words.empty?
      number.to_s
    else
      words.select{|word| word != nil}.join('')
    end
  end

  # Thanks to 200_success on codereview.stackexchange.com for
  # this bit of code.
  def self.prime_factorization(n)
    Prime.prime_division(n).flat_map {|factor, power| [factor] * power}
  end
end
