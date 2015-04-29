def compute(x, y)
    xs = x.split('')
    ys = y.split('')
    ham = 0
    xs.count.times do |num|
      if xs.count <= ys.count && xs[num] != ys[num]
        ham +=1
          end
        end
    ham
  end