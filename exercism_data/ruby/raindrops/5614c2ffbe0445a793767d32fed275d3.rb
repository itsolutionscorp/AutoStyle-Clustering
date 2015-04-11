class Raindrops

  DROPS = {
    3 => "Pling",
    5 => "Plang",
    7 => "Plong"
  }

  def self.convert(num)
    drop_select = DROPS.select{ |key| num % key == 0 }
    drop_select.empty? ? num.to_s : drop_select.values.join
  end

end
