class Raindrops
  def self.convert(number)
    factors = [3, 5, 7]
    voyels = %w(i a o)
    result = ''
    factors.length.times do |i|
      result << "Pl#{voyels[i]}ng" if number % factors[i] == 0
    end
    result == '' ? number.to_s : result
  end
end
