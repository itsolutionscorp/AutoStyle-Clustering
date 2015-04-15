def compute(x, y)
    return 0 if x == y


    xx, yy = x.scan(/\w/), y.scan(/\w/)





    xx.

      fill{ |i| [xx[i], yy[i] || xx[i]] }.



      map(&:uniq).

      count { |a| a.size != 1 }