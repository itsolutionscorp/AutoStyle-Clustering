class Raindrops

  @translation = {
    3 => "Pling",
    5 => "Plang",
    7 => "Plong"
  }

  def self.convert(number)
    result = prime_factors(number).uniq.map do |factor|
      @translation[factor]
    end.join
    result == "" ? number.to_s : result
  end

  private

  def self.prime_factors(number)
    (2..number).each do |factor|
      if number / factor * factor == number
        return [factor] | prime_factors(number / factor)
      end
    end
    return []
  end

end
