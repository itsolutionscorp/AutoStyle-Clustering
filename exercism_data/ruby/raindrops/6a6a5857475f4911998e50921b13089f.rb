class Raindrops

  def self.convert(int)
    output = ""
    output += 'Pling' if int % 3 == 0
    output += 'Plang' if int % 5 == 0
    output += 'Plong' if int % 7 == 0
    output += int.to_s if output.empty?
    output
  end

end
