module Raindrops
  def self.convert(i)
    result = ""
    result << "Pling" if (i % 3).zero?
    result << "Plang" if (i % 5).zero?
    result << "Plong" if (i % 7).zero?
    result = i.to_s if result.empty?
    result
  end
end
