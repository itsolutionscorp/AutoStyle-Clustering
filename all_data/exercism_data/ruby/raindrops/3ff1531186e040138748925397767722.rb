class Raindrops

  THE_RAINDROPS = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }

  def self.convert(n)
    output = THE_RAINDROPS.values_at(*THE_RAINDROPS.keys.select{|k| n % k == 0}).join
    output.empty? ? n.to_s : output
  end

end
