module Raindrops
  def self.convert(n)
    result = ''

    result << 'Pling' if (n % 3).zero?
    result << 'Plang' if (n % 5).zero?
    result << 'Plong' if (n % 7).zero?

    if result.empty?
      n.to_s
    else
      result
    end
  end
end
