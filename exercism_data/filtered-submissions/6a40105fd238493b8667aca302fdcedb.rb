def compute(a, b)


    diff ||= 0


    first = a.split("")
    second = b.split("")


    length = first.length


    length.times do |x|
      unless first[x] == second[x]
        diff += 1
      end
    end


    diff

  end