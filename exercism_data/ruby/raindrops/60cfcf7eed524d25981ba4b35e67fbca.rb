class Raindrops
  def self.convert(num)
    resolve("#{pling(num)}#{plang(num)}#{plong(num)}", num)
  end

  def self.pling(num)
    (num % 3).zero? ? 'Pling' : ''
  end

  def self.plang(num)
    (num % 5).zero? ? 'Plang' : ''
  end

  def self.plong(num)
    (num % 7).zero? ? 'Plong' : ''
  end

  def self.resolve(result, num)
    result.empty? ? num.to_s : result
  end
end
