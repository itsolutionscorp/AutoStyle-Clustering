class Raindrops
  SOUNDS = [ { prime: 3, sound: 'Pling' },
             { prime: 5, sound: 'Plang' },
             { prime: 7, sound: 'Plong' }]

  def self.convert(number)
    divisors = SOUNDS.select do |el|
      number % el[:prime] == 0
    end

    if divisors.any?
      divisors.reduce('') { |a, e| "#{a}#{e[:sound]}" }
    else
      number.to_s
    end
  end
end
