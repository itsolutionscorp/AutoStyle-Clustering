require 'prime'

class Raindrops
  def self.convert(number)

    raindrop_messages = {
      3 => 'Pling',
      5 => 'Plang',
      7 => 'Plong'
    }
    message = number.prime_division.map do |factors|
      factors.map do |factor|
        raindrop_messages[factor]
      end
    end.join
    message.empty?? number.to_s : message
  end
end
