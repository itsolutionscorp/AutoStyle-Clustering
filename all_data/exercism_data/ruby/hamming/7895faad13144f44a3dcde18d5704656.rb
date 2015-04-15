class Hamming
  def self.compute(strand1, strand2)
    DNA.new(strand1).hamming_distance strand2
  end
end

class DNA
  attr_reader :strand1
  def initialize(strand1 = '')
    @strand1 = strand1
  end

  def hamming_distance(strand2 = '')
    bases1 = strand1.chars
    bases2 = strand2.chars
    diff_count(bases1, bases2)
  end

  def diff_count(list1, list2)
    count = 0
    a,b = order(list1, list2)
    a.size.times {|i| count += 1 if a[i] != b[i] }
    count
  end

  def order(list1, list2)
    list1.size > list2.size ? [list2, list1] : [list1, list2]
  end
end
