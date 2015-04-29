class Raindrops
  TABLE = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }

  def self.convert(int)
    res = TABLE.each_with_object('') { |(k, v), a| a << v if int % k == 0 }
    res.empty? ? int.to_s : res
  end
end
