def compute(x, y)
    x, y = x.split(''), y.split('')


    if x.length > y.length
      x = x.slice(0..-(x.length - y.length + 1))
    elsif x.length < y.length
      y = y.slice(0..-(y.length - y.length + 1))
    end


    differences_count = 0
    (0..(x.length - 1)).each do |i|
      differences_count += 1 if x[i] != y[i]
    end
    differences_count
  end