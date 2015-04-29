class Raindrops
  def self.convert n
    result = ""
    { 3 => "Pling", 5 => "Plang", 7 => "Plong" }
      .each { |num, val| result += val if n % num == 0 }
    result.empty? ? n.to_s : result
  end
end
