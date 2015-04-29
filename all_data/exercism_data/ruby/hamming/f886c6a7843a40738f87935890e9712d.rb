# dna.map{|d| d.split('')}[0].zip(d2[1]).map(&:uniq).select{|d|d.count > 1}.count

class Hamming
  def self.compute a, b
    seqs = [a,b].map{|d| d.split('')}
    seqs[0].zip(seqs[1]).delete_if{|s| s.include?(nil)}.map(&:uniq).select{|d|d.count > 1}.count
  end
end
