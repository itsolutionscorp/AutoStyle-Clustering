class Hamming
  attr_reader :a, :b

  class << self

  def compute(a, b)
    @non_matches = 0
    c = a.length - 1
    d = b.length - 1
    if a.length > b.length
      a = a[0..d]
    elsif b.length > a.length
      b = b[0..c]
    end
    @new_a = a.split("")
    @new_b = b.split("")
    check_for_matches
    @non_matches
  end

  def check_for_matches
    if @new_a.first != @new_b.first
      add_to_non_matches
    end
    if @new_a[1] != @new_b[1]
      add_to_non_matches
    end
    if @new_a[2] != @new_b[2] 
      add_to_non_matches
    end
    if @new_a[3] != @new_b[3] 
      add_to_non_matches
    end
    if @new_a[4] != @new_b[4] 
      add_to_non_matches
    end
    if @new_a[5] != @new_b[5] 
      add_to_non_matches
    end  
    if @new_a[6] != @new_b[6] 
      add_to_non_matches
    end
    if @new_a[7] != @new_b[7] 
      add_to_non_matches
    end
    if @new_a[8] != @new_b[8] 
      add_to_non_matches
    end
    if @new_a[9] != @new_b[9] 
      add_to_non_matches
    end
    if @new_a[10] != @new_b[10] 
      add_to_non_matches
    end
    if @new_a[11] != @new_b[11] 
      add_to_non_matches
    end
    if @new_a[12] != @new_b[12]
      add_to_non_matches
    end 
  end

  def add_to_non_matches
    @non_matches += 1
  end

  end
end
