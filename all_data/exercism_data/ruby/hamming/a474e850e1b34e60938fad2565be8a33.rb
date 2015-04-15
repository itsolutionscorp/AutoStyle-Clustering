class Hamming
  def self.compute(a, b)
    a = a.size > b.size ? a[0...-1] : a
    new_a = a.scan(/\w/).zip b.scan(/\w/)
    new_a.map(&:uniq).delete_if {|a| a.count == 1 }.count
  end
end
