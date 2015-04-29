class Raindrops
  P = [3, 5, 7]
  W = %w(Pling Plang Plong)

  def self.convert(n)
    str = P.each_with_index.with_object("") {|(p,i), str| str << W[i] if n % p == 0}
    str = n.to_s if str.empty?
    str
  end
end
