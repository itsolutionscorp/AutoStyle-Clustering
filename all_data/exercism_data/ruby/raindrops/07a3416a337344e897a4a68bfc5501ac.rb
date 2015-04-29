class Raindrops
  def self.convert(number)
    result = ''

    result << 'Pling' if (number % 3).zero?
    result << 'Plang' if (number % 5).zero?
    result << 'Plong' if (number % 7).zero?

    result.empty? ? number.to_s : result
  end
end
