class Raindrops

  def self.convert(n)
    s = (0..2).map { |i| "Pl#{%w(i a o)[i]}ng" if n % [3, 5, 7][i] < 1 } * ''
    s == '' ? n.to_s : s
  end

end
