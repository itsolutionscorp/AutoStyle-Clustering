def compute(x,y)
    dist=0
    (0...[x.length, y.length].min).count do |i|
      if x[i]!=y[i]
        dist+=1
      end
    end
  end