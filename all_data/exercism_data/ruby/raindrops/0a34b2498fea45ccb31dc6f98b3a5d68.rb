class Raindrops
  def self.convert number
    result = ''
    result << "Pling" if number.modulo(3).zero?
    result << "Plang" if number.modulo(5).zero?
    result << "Plong" if number.modulo(7).zero?
    result << number.to_s if result.empty?
    result
  end
end
