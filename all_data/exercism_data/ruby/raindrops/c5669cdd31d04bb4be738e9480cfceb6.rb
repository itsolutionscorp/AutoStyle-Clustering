class Raindrops
  def self.convert(num)
    sound = ""
    it_makes_noise = [num%3==0,
      num%5==0,
      num%7==0
    ]

    if !(it_makes_noise.any?)
      return num = num.to_s.gsub(/(\d)(?=(\d\d\d)+(?!\d))/, "\\1#{''}")
    end

    sound+="Pling" if it_makes_noise[0]
    sound+='Plang' if it_makes_noise[1]
    sound+='Plong' if it_makes_noise[2]
    sound
  end
end
