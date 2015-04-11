class Raindrops
  def self.convert(n)
    { 3 => "Pling", 5 => "Plang", 7 => "Plong" }.map do |k,v|
      n % k == 0 ? v : ""
    end.join[/.+/m] || "#{n}"
  end
end
