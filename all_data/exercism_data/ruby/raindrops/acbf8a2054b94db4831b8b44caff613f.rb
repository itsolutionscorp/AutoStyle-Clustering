class Raindrops
  CONVERT = [
    [3, "Pling"],
    [5, "Plang"],
    [7, "Plong"]
  ]
  def self.convert(num)
    str = CONVERT.map do |m|
      val, sound = m
      sound if num % val == 0
    end.join
    str = num.to_s if str.size == 0
    str
  end
end
