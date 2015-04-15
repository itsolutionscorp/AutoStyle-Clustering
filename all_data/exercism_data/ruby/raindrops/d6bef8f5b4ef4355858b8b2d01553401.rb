class Raindrops
  def self.convert(input)
    drops = to_raindrops(input)
    drops == "" ? input.to_s : drops
  end

  private

  DROPS = [[3,"Pling"],[5,"Plang"],[7,"Plong"]]

  def self.to_raindrops(input)
    DROPS.reduce("") do |out, tup|
      (input % tup[0] == 0) ? (out + tup[1]) : out
    end
  end
end
