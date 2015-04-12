class Hamming
  def compute str_a, str_b
    if !str_a || !str_b || str_a.size != str_b.size
      raise ArgumentError "Can compare only two strings of same length"  
    end
    
    diffs = 0
    arr_a = str_a.split ''
    arr_a.each.with_index do |ele, index|
      diffs += 1 if ele != str_b[index]
    end
    diffs
  end
end
