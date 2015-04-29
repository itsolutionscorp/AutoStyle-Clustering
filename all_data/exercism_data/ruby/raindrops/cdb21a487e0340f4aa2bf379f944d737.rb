class Raindrops
  def self.convert(value)
    result = String.new
    result << 'Pling' if (value % 3).zero?
    result << 'Plang' if (value % 5).zero?
    result << 'Plong' if (value % 7).zero?

    result.empty? ? value.to_s : result
  end
end
