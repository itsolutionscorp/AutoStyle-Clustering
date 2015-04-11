class Raindrops
  PRIME_CONVERSION = {
      3 => 'Pling', 
      5 => 'Plang', 
      7 => 'Plong'
    }

  def self.convert(number)
    raindrop_translation = nil

    PRIME_CONVERSION.keys.each do |prime_number|
      if number % prime_number == 0 
        raindrop_translation  = raindrop_translation.to_s + 
                                PRIME_CONVERSION[prime_number]
      end
    end

    raindrop_translation || number.to_s
  end
end
