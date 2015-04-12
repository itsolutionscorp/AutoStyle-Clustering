class Hamming
  def compute(a, b)
    al = a.length
    bl = b.length
    if al > bl
      a = a.slice!(0..bl-1)
    elsif al < bl
      b = b.slice!(0..al-1)
    end
    asplit = a.chars
    bsplit = b.chars
    count = 0
    hammy = asplit.each_with_index {|item, index| count += 1 if asplit[index] != bsplit[index] }
    return count
  end
end
