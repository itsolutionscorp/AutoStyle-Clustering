def compute(a, b)
    if b.length > a.length then b = b[0, a.length] end
    b.split("").zip(a.split("")).inject(0) { |sum, pair| if pair.first != pair.last then sum + 1 else sum end }
  end     

end