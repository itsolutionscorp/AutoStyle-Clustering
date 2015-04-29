module Raindrops

  CONVERT = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }

  def self.convert nb
    res = CONVERT.select { |key, _| nb % key == 0 }.values.join
    res.empty? and nb.to_s or res
  end

end
