class Raindrops
  def self.convert(num)
    matched_factors = factors.select do |factor, _|
      num % factor == 0
    end
    matched_factors.empty? ? num.to_s : matched_factors.values.join
  end

  def self.factors
    {
      3 => "Pling",
      5 => "Plang",
      7 => "Plong"
    }
  end

  private_class_method :factors
end
