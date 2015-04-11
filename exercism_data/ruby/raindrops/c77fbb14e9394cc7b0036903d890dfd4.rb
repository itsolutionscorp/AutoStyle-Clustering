class Raindrops
  DROP_SOUNDS = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }

  def self.convert(provided_number)
    sounds = DROP_SOUNDS.map do |number, drop|
      drop if provided_number % number == 0
    end.join
    sounds.empty? ? provided_number.to_s : sounds
  end
end
