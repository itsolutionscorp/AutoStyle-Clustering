class Raindrops

  def self.convert(n)
    result = ""
    result += "Pling" if 0 == n % 3
    result += "Plang" if 0 == n % 5
    result += "Plong" if 0 == n % 7
    result = "#{n}" if result.empty?
    result
  end
end
