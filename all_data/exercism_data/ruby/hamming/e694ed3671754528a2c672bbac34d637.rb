module Hamming
  class << self
    def compute(s1, s2)
      different_pairs(s1,s2).length
    end
  
    def different_pairs(s1, s2)
      to_pair_list(s1,s2).select { |(x,y)| x != y }
    end
  
    def to_pair_list(s1, s2)
      a1 = strand_to_array(s1)
      a2 = strand_to_array(s2)
  
      # truncate to shorter strand's length...
      min = [a1,a2].map(&:length).min
      a2 = a2[0,min]
      a1 = a1[0,min]
  
      # ...and zip together
      a1.zip(a2)
    end
  
    # Given array or string, we want an array.
    def strand_to_array(s)
      s.respond_to?(:to_a) ? s.to_a : s.split('')
    end
  end  
end
  
