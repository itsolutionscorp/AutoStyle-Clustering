class Raindrops
  DROPS = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }

  def self.convert(number)
    output = DROPS.each_with_object('') do |(factor, drop), output|
      output << drop if number % factor == 0
    end
    output.empty? ? number.to_s : output
  end
end
