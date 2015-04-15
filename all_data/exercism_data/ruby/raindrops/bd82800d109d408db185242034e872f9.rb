class Raindrops

  FACTORS = {3 => 'Pling', 5 => 'Plang', 7 => 'Plong'}
  
  def self.convert(number)
    found_factors = factors number
    found_factors = (found_factors.uniq || [])
    found_factors.select! { |factor| FACTORS.include? factor}
    if found_factors.empty?
      number.to_s
    else
      word = ""
      found_factors.each do |factor|
        word << FACTORS[factor]
      end
      word
    end
  end

  def self.factors(number)
    found_factors = []
    while factor = FACTORS.keys.find { |factor| number % factor == 0 }
      found_factors << factor
      number /= factor 
    end
    found_factors
  end

end
