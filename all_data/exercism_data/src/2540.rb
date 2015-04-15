def compute(x, y)
        small = [x.length, y.length].min
        pairs = x.split("").take(small).zip(y.split("").take(small))
        different = pairs.reject {|pair| pair.uniq.length == 1}
        different.length
    end