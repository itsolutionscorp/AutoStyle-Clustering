def compute(a,b)
    c = a.split(//).zip(b.split(//))
    c.reject { |a,b| a == b || a.nil? || b.nil?}.length
  end