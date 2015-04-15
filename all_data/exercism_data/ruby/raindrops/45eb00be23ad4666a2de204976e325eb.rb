class Raindrops

  def self.convert(string)
    output = ''
    factor = [3, 5, 7].select { |f| self.is_factor?(string, f)}
    factor.each { |r| output << { 3 => "Pling", 5 => "Plang", 7 => "Plong" }[r]}
    output << string.to_s if output.empty?
    output
  end

  private

  def self.is_factor?(string, factor)
    string % factor == 0
  end

end
