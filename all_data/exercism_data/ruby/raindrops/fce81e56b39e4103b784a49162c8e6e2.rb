class Raindrops
  CONVERSIONS = {
    3 => "Pling",
    5 => "Plang",
    7 => "Plong"
  }

  def self.convert(n)
    s = CONVERSIONS.each_with_object("") do |(factor,sound), s|
       s << sound if n % factor == 0
    end

    s.empty? ? n.to_s : s
  end
end
