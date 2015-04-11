class Raindrops

  Factors = {3 => 'Pling', 5 => 'Plang', 7 => 'Plong'}

  def self.convert num
    result = ''
    Factors.keys.sort.each do |f|
      if num % f == 0
        result+=Factors[f]
      end
    end
    result.empty? ? num.to_s : result
  end

end
