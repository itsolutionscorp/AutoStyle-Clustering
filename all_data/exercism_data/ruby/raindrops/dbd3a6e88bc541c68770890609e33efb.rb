class Raindrops
  DROPS = {
    3 => "Pling",
    5 => "Plang",
    7 => "Plong"
  }

  def self.convert(number)
    result = ""
    DROPS.each { |factor,drop|
      result << drop if get_factors(number).include?(factor)
    }
    result.empty? ? number.to_s : result
  end

  private

  def self.get_factors(number)
    @factors ||= Hash.new { |hash, key|
      hash[key] = (1..key/2).map { |num|
        factor = key / num.to_f
        [num, factor.to_i] if factor == factor.to_i
      }.flatten
    }
    @factors[number]
  end
end
