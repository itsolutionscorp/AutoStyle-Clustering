class Raindrops

  def self.convert( number )
    noise_factors = {
       'Pling' => 3,
       'Plang' => 5,
       'Plong' => 7
    }
    output = ""
    noise_factors.each do |noise, factor|
      if number % factor == 0
        output += noise
      end
    end
    if output.empty?
      return number.to_s
    else
      return output
    end
  end

end
