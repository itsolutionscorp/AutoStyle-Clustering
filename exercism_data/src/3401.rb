def compute first, second
    return 0 if first == second
    count, length, fl, sl = 0, first.length, first.split(//), second.split(//)
    (0...length).each do |i|
      if fl[i] != sl[i]
        count += 1
      end
    end
    count
  end