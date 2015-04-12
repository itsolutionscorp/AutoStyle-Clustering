def compute(a, b)
    b_ary = b.split('')
    a.split('').keep_if do |a_el|
      b_el = b_ary.shift
      !b_el.nil? && (a_el != b_el)
    end.size
  end