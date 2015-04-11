class Raindrops
  def self.convert(n)
    string = ""

    { i: 3, a: 5, o: 7 }.each do |k, v|
      string += "Pl#{k}ng" if n % v == 0
    end

    string = n.to_s if string.empty?

    string
  end
end
