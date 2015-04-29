class Raindrops

  CONVERSION = {
      3 => 'Pling',
      5 => 'Plang',
      7 => 'Plong'
  }

  def self.convert number
    result = transform factoring number

    result.empty? ? number.to_s : result
  end

  def self.factoring number
    (2..number).each_with_object [] do |divident, result| 
      while number % divident == 0
        number = number / divident
        result << divident
      end
    end
  end

  def self.transform factors
    factors.uniq.each_with_object "" do |factor, result|
      result << CONVERSION[factor] if CONVERSION.has_key? factor
    end
  end
end
