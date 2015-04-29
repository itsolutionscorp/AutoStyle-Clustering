class Hamming
  def self.compute(str, to)
    str.split(//).each_with_index.map do |n, i|
      (to[i] && n != to[i]) ? 1 : 0
    end.inject(0, :+)
  end
end
