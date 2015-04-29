module Raindrops
  @maps = {
      3 => 'Pling',
      5 => 'Plang',
      7 => 'Plong'
  }
  def self.convert(number)
    result = ''
    @maps.each_pair do |k, v|
      result += v if number % k == 0
    end
    if result.empty?
      number.to_s
    else
      result
    end

  end
end

puts Raindrops.convert 100000000
