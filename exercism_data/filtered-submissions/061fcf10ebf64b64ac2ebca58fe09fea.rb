class Hamming
  def compute(a,b)
    arr_a = a.split("")
    arr_b = b.split("")
    arr_a, arr_b = arr_b, arr_a if arr_a.size < arr_b.size
    arr_a = arr_a[0, arr_b.size]
    arr_a.zip(arr_b).reduce(0) { |memo, (ea,eb)|  memo + (ea == eb ? 0 : 1) }
  end
end
