def compute(a,b)

    aa = a.split('')
    ba = b.split('')


    h  = 0


    index_range = (0..((aa.length > ba.length ? ba.length : aa.length)-1))


    index_range.each do |i|


      if ba[i] != aa[i]

        h += 1
      else

        next
      end
    end

    return h
  end