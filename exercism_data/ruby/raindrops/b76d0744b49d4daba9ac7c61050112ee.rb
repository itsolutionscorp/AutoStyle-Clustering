class Raindrops
  RAINDROPS = {
    'Pling' => -> (number) { (number % 3) == 0 },
    'Plang' => -> (number) { (number % 5) == 0 },
    'Plong' => -> (number) { (number % 7) == 0 }
  }

  def self.convert(number)
    return number.to_s unless RAINDROPS.any? { |_sound, condition| condition.call(number) }
    RAINDROPS.map do |sound, condition|
      sound if condition.call(number)
    end.join
  end
end
