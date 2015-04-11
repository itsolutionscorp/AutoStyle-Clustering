class Raindrops
  FACTORS = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }

  def self.convert(number)
    output = FACTORS.each_with_object("") do |(factor, string), result|
      result << string if (number % factor == 0)
    end
    output.empty? ? number.to_s : output
  end
end
