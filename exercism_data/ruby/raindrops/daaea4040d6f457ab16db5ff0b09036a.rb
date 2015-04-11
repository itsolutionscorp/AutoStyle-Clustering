class Raindrops
  SOUNDS = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong',
  }

  def self.convert(integer)
    str = SOUNDS.each_with_object('') do |(divider, sound), str|
      str << sound if integer % divider == 0
    end

    str.empty? ? integer.to_s : str
  end
end
