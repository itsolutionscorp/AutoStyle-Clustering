class Raindrops
  def self.convert(num)
    hsh = {3 => "Pling", 5 => "Plang", 7 => "Plong"}
    result = hsh.each_with_object("") { |(k, v), str| str << v if num % k == 0 }
    result.empty? ? num.to_s : result
  end
end
