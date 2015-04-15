class Raindrops
  DROPS = {
    3 => "Pling",
    5 => "Plang",
    7 => "Plong"
  }

  def self.convert(number)
    result = ""
    DROPS.each { |factor,drop|
      result << drop if number % factor == 0
    }
    result.empty? ? number.to_s : result
  end
end
