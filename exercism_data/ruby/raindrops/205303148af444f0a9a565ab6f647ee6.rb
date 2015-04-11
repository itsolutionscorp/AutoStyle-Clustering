class Raindrops
  def self.convert(rain)
    retval = {3 => "Pling", 5 => "Plang", 7 => "Plong"}.each_pair.collect do|factor, str| 
        str if rain % factor == 0 
    end.compact.join

    retval = rain.to_s if retval.length == 0
    retval
  end
end
