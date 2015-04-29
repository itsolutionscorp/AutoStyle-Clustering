class Raindrops
  def self.convert(s)
    "".tap do |r|
      r << "Pling" if (s%3 == 0)
      r << "Plang" if (s%5 == 0)
      r << "Plong" if (s%7 == 0)
      r << s.to_s if (r.empty?)
    end
  end
end
