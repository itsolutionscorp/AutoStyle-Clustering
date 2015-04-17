def compute(a,b)
    hamming = 0
    a.split(//).zip(b.split(//)).each do |strand|
      return hamming if strand.any? {|strand| strand.nil?}
      strand.first == strand.last ? hamming : hamming += 1
    end
    hamming
  end