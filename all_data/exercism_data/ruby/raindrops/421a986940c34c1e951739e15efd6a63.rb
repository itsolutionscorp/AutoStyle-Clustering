class Raindrops
  
  def convert(input)
    raindrops = ""    
    drop_test = { 3 => "Pling", 5 => "Plang", 7 => "Plong" } 

    drop_test.each do |number, value|
      if (input % number) == 0 then
        raindrops.concat(value)
      end
    end
    raindrops.empty? ? input.to_s : raindrops
  end

end
