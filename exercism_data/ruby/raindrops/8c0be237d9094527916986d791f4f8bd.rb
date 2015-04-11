class Raindrops
  def self.convert(n)
    response = ''

    response << 'Pling' if (n % 3).zero?
    response << 'Plang' if (n % 5).zero?
    response << 'Plong' if (n % 7).zero?

    response.empty? ? n.to_s : response
  end
end
