class Raindrops
  def self.convert(input)
    drops = to_raindrops(input)
    drops == "" ? input.to_s : drops
  end

  DROPS = {3=>"Pling",5=>"Plang",7=>"Plong"}

  private
  def self.to_raindrops(input)
    DROPS.reduce("") do |out, (base, drop)|
      (input % base == 0) ? (out + drop) : out
    end
  end
end
