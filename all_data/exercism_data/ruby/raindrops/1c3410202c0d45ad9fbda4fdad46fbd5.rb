class Raindrops

  def self.convert(num)
    raindrop = ''
    while(num % 3 == 0 || num % 5 == 0 || num % 7 == 0)
      if num % 3 == 0
        raindrop += "Pling" if !raindrop.include?("Pling")
        num = num/3
      elsif num % 5 == 0
        raindrop += "Plang" if !raindrop.include?("Plang")
        num = num/5
      elsif num % 7 == 0
        raindrop += "Plong" if !raindrop.include?("Plong")
        num = num/7
      end
    end

    raindrop += num.to_s if(raindrop == '')

    return raindrop
  end

end
