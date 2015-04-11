module Hamming
  extend self

  def compute a,b
    a,b = b,a if a.length > b.length

    a = a.chars
    b = b.chars

    c = 0
    while a.length != 0
      ca = a.shift
      cb = b.shift
      c += 1 if ca != cb
    end
    c
  end

end
