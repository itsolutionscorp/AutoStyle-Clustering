class Raindrops
  def self.convert(num)
    response = ''
    response << 'Pling' if (num % 3).zero?
    response << 'Plang' if (num % 5).zero?
    response << 'Plong' if (num % 7).zero?
    return response unless response.empty?
    num.to_s
  end
end
