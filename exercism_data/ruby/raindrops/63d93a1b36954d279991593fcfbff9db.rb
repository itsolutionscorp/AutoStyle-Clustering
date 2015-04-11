class Raindrops
  
  def convert(input)
  raindrops = ""
  if (input % 3) == 0 then  
    raindrops.concat("Pling")
  end
  if (input % 5) == 0 then  
    raindrops.concat("Plang")
  end
  if (input % 7) == 0 then 
    raindrops.concat("Plong") 
  end
   raindrops.empty? ? input.to_s : raindrops
  end

end
