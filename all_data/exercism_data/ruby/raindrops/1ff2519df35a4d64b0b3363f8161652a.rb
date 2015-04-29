class Raindrops
  MAP = {
    3 => "Pling",
    5 => "Plang",
    7 => "Plong"
  }

  def self.convert(input)
    output = ""
    MAP.each { |k, v| output << v if input % k == 0 }
    output.length == 0 ? input.to_s : output
  end
end
