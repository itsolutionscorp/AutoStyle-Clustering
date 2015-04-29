class Raindrops
  DROP_SPEAK = {
      3 => "Pling",
      5 => "Plang",
      7 => "Plong",
  }

  def self.convert(number)
    drop = number.to_s

    factors = []

    DROP_SPEAK.each_key do |factor|
      factors << factor if number%factor == 0
    end

    if factors.length > 0
      drop = ''
      factors.each do |f| 
        drop += DROP_SPEAK[f]
      end
    end

    drop
  end
end
