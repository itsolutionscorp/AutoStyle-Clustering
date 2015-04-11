class Raindrops
  def self.convert(num)
    raindrop_speak = []
    if num % 3 == 0
      raindrop_speak << 'Pling'
    end
    if num % 5 == 0
      raindrop_speak << 'Plang'
    end
    if num % 7 == 0
      raindrop_speak << 'Plong'
    end
    if raindrop_speak.length > 0
      raindrop_speak.join
    else
      num.to_s
    end
  end
end
