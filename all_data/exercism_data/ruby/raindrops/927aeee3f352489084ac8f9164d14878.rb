class Raindrops

  def self.convert(number)
    raindrops_key = {3 => "Pling", 5 => "Plang", 7 => "Plong"}
    output = raindrops_key.inject("") {|str,(k, v)| str << (number % k == 0 ? v : "")}
    output == "" ? number.to_s : output
  end

end
