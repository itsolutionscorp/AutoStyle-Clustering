class Raindrops

  @primes_to_rain = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }

  def self.convert(in_value)
    output = @primes_to_rain.select { |i| in_value % i == 0 }.values.join
    output == "" ? in_value.to_s : output
  end
end
