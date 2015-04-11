module Hamming
  def self.compute xs, ys
    xs.codepoints.zip(ys.codepoints).count { |(x, y)| x != y unless y.nil? }
  end
end
