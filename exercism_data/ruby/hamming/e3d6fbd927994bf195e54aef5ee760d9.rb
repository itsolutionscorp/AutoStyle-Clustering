class Hamming
  def self.compute a, b
    seqs = [a,b].map{|d| d.split('')}
    seqs[0].zip(seqs[1]).delete_if{|s| s.include?(nil)}.map(&:uniq).select{|d|d.count > 1}.count
  end
end
