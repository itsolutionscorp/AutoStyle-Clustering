module Hamming
  def self.compute(*strings)
    (0...strings.map(&:size).min).select {|i| strings.map {|s| s[i]}.uniq.size != 1 }.size
  end
end
