class SpaceAge
  attr_accessor :age, :earth, :hash
  
  def initialize(age)
  	@age = age
  	@earth = 31557600
  	@hash = {"mercury" =>0.2408467,
    	  "venus" => 0.61519726,
    	  "mars" =>1.8808158,
    	  "jupiter"=> 11.862615,
    	  "saturn" => 29.447498,
    	  "uranus" => 84.016846,
    	  "neptune" =>164.79132}
  end

  def seconds
  	age
  end

  def on_earth
  	(age/earth.to_f).round(2)
  end

  def on_mercury
  	(age/(hash["mercury"] * earth.to_f)).round(2)
  end

  def on_venus
  	(age/(hash["venus"] * earth.to_f)).round(2)
  end

  def on_mars
  	(age/(hash["mars"] * earth.to_f)).round(2)
  end

  def on_jupiter
  	(age/(hash["jupiter"] * earth.to_f)).round(2)
  end

  def on_saturn
  	(age/(hash["saturn"] * earth.to_f)).round(2)
  end

  def on_uranus
  	(age/(hash["uranus"] * earth.to_f)).round(2)
  end

  def on_neptune
  	(age/(hash["neptune"] * earth.to_f)).round(2)
  end


end
