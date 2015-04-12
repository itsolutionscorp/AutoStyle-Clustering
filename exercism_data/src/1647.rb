module Hamming
  def compute(a, b)
    return false if [a,b].include?(nil)

    max_compare_length = [a.length, b.length].min
    [].tap do |out|
      (0...max_compare_length).each do |idx|
        out << (a[idx] == b[idx] ? 0 : 1)
      end
    end.reduce(:+)
  end
end
