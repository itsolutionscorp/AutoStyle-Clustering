class Raindrops
  SOUNDS = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }

  def self.convert(number)
    divisors = SOUNDS.select do |key, value|
      number % key == 0
    end

    if divisors.size > 0
      divisors.reduce('') { |a, e| "#{a}#{e[1]}" }
    else
      number.to_s
    end
  end
end
