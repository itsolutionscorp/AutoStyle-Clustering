class Hamming
  def self.prepare(str)
    str.split("")
  end

  def self.compute(str_a, str_b)
    array_a = prepare(str_a)
    array_b = prepare(str_b)
    array_a, array_b = array_b, array_a if array_a.size < array_b.size
    array_a = array_a.take(array_b.size)
    array_a.zip(array_b).reduce(0) do |memo, (ea,eb)|
      memo + (ea == eb ? 0 : 1)
    end
  end
end
