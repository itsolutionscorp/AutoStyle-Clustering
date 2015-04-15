def compute a, b
    if a.length > b.length
      a = a[0..(b.length - 1)]
    elsif a.length < b.length
      b = b[0..(a.length - 1)]
    end
    a.chars.zip(b.chars).inject(0) {|c, p| c + p.uniq.length - 1 }
  end
end