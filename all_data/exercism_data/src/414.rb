def compute(strand1,strand2)
    comparison = []
    comparison << strand1.split(//) << strand2.split(//)
    comparison.transpose.count{|pair| pair.uniq.length != 1}
  end