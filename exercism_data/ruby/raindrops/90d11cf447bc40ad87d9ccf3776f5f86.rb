class Raindrops
  CONV_STRS = %w(Pling Plang Plong)
  def self.convert(i)
    raindrops = [3, 5, 7].map.with_index do |d, idx|
      CONV_STRS[idx] if i % d == 0
    end.compact.join
    raindrops = i.to_s if raindrops.empty?
    raindrops
  end
end
