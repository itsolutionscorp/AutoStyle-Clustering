class Hamming
  def self.compute(*strands)
    min_len = strands.map {|s| s.length}.min
    strands.map! {|s| s[0..min_len-1].split("")}
    
    num_mismatches = 0
    strands[0].each_index do |i|
      num_mismatches += strands[0][i] == strands[1][i] ? 0 : 1
    end
    num_mismatches
  end
end
