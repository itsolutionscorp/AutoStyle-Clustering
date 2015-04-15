class Hamming
  def self.compute(a, b)
    
    c = "";
    hamming_count = 0;
    
    if (a.length < b.length) 
      c = a;
    else
      c = b;
    end

    for i in 0..c.length 
          if a[i] != b[i]
            hamming_count += 1;
          end

    end


    
    return hamming_count;
  end
end
